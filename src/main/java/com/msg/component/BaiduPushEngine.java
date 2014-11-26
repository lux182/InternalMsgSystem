package com.msg.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.BindInfo;
import com.baidu.yun.channel.model.DeleteAppIoscertRequest;
import com.baidu.yun.channel.model.DeleteTagRequest;
import com.baidu.yun.channel.model.InitAppIoscertRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.QueryUserTagsRequest;
import com.baidu.yun.channel.model.QueryUserTagsResponse;
import com.baidu.yun.channel.model.SetTagRequest;
import com.baidu.yun.channel.model.TagInfo;
import com.baidu.yun.channel.model.UpdateAppIoscertRequest;
import com.baidu.yun.channel.model.VerifyBindRequest;
import com.msg.domain.UserDevice;
import com.msg.enums.Cfg;
import com.msg.enums.MessageType;
import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;
import com.msg.repo.CfgRepo;
import com.msg.repo.UserDeviceRepo;
import com.msg.utils.NormalException;
import com.msg.utils.SystemMessage.Hint;
import com.msg.wrapper.DeviceBindInfo;
import com.msg.wrapper.NotificationMessage;

@Component
public class BaiduPushEngine extends SendEngine {

	@Resource
	UserDeviceRepo userDeviceRepo;
	
	@Resource
	CfgRepo cfgRepo;
	
	public BaiduPushEngine() {
		this.register(SendChannel.BAIDU_PUSH);
	}

	@Override
	public void send(SendMessageEvent event) {
		NotificationMessage msg = new NotificationMessage();
		msg.setTitle(event.getTitle());
		msg.setDescription(event.getContent());
		if(StringUtils.isEmpty(event.getUrl())){
			msg.setOpenType(2);
		}else{
			msg.setUrl(event.getUrl());
			msg.setOpenType(1);
		}
		
		if(MessageType.PUBLIC.equals(event.getType())){
			if(this.pushBroadcastNotificationMessage(msg)==0){
				throw new NormalException(Hint.BAIDU_PUSH_ERROR);
			}
		}else if(MessageType.PRIVATE.equals(event.getType())){
			UserDevice device = userDeviceRepo.findByAid(event.getRecId());
			if(device!=null){
				if(this.pushUnicastNotificationMessage(device.getUserId(), device.getChannelId(), msg)==0){
					throw new NormalException(Hint.BAIDU_PUSH_ERROR);
				}
			}
		}
	}
	
	
	private BaiduChannelClient channelClient = null;
    

    public enum MESSAGE_TYPE {
        IN_APP, NOTIFICATION;
    }

    public enum DEPLOY_STATUS {
        NONE, DEVELOPMENT, PRODUCTION;
    }

    public enum DEVICE_TYPE {
        NONE, BROWSER, PC, ANDROID, IOS, WINPHONE;
    }

    @PostConstruct
    @Override
    public void initEngine() {
        channelClient = new BaiduChannelClient(new ChannelKeyPair(
        		cfgRepo.findByCfgKey(Cfg.BAIDU_PUSH_API_KEY.name()).getCfgVal(),
        		cfgRepo.findByCfgKey(Cfg.BAIDU_PUSH_SECRET_KEY.name()).getCfgVal()));
    }
    
    public void initIOSCert(String name, String description, String devCert, String releaseCert) {
        try {
            InitAppIoscertRequest request = new InitAppIoscertRequest();
            request.setName(name);
            request.setDescription(description);
            request.setDevCert(devCert);
            request.setReleaseCert(releaseCert);
            channelClient.initAppIoscert(request);
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }
    }

    public void updateIOSCert(String name, String description, String devCert, String releaseCert) {
        try {
            UpdateAppIoscertRequest request = new UpdateAppIoscertRequest();
            request.setName(name);
            request.setDescription(description);
            request.setDevCert(devCert);
            request.setReleaseCert(releaseCert);
            channelClient.updateAppIoscert(request);
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }
    }

    public void deleteIOSCert() {
        try {
            DeleteAppIoscertRequest request = new DeleteAppIoscertRequest();
            channelClient.deleteAppIoscert(request);
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }
    }

    public List<DeviceBindInfo> getBindList(String userId) {

        try {
            QueryBindListRequest request = new QueryBindListRequest();
            request.setUserId(userId);

            QueryBindListResponse response = channelClient.queryBindList(request);
            List<BindInfo> bindInfos = response.getBinds();
            List<DeviceBindInfo> bindList = new ArrayList<DeviceBindInfo>();
            for (BindInfo bindInfo : bindInfos) {
                bindList.add(new DeviceBindInfo(bindInfo));
            }

            return bindList;
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }

        return null;
    }

    public boolean verifyBind(String userId, Long channelId, Integer deviceType) {
        try {
            VerifyBindRequest request = new VerifyBindRequest();
            request.setUserId(userId);
            request.setChannelId(channelId);
            request.setDeviceType(deviceType);
            channelClient.verifyBind(request);

            return true;
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }

        return false;
    }

    /**
     * Push an unicast notification message to a device identified by bindInfo
     * 
     * @param bindInfo
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushUnicastNotificationMessage(DeviceBindInfo bindInfo, NotificationMessage message) {
        return pushUnicastMessage(bindInfo.getUserId(), bindInfo.getChannelId(), message.toString(),
                MESSAGE_TYPE.NOTIFICATION);
    }

    /**
     * Push an unicast in-App message to a device identified by bindInfo
     * 
     * @param bindInfo
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushUnicastInAppMessage(DeviceBindInfo bindInfo, String message) {
        return pushUnicastMessage(bindInfo.getUserId(), bindInfo.getChannelId(), message, MESSAGE_TYPE.IN_APP);
    }

    /**
     * Push an unicast notification message to a device identified by bindInfo
     * 
     * @param userId
     * @param channelId
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushUnicastNotificationMessage(String userId, Long channelId, NotificationMessage message) {
        return pushUnicastMessage(userId, channelId, message.toString(), MESSAGE_TYPE.NOTIFICATION);
    }

    /**
     * Push an unicast in-App message to a device identified by bindInfo
     * 
     * @param userId
     * @param channelId
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushUnicastInAppMessage(String userId, Long channelId, String message) {
        return pushUnicastMessage(userId, channelId, message, MESSAGE_TYPE.IN_APP);
    }

    private int pushUnicastMessage(String userId, Long channelId, String message, MESSAGE_TYPE messageType) {
        try {
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();

            request.setChannelId(channelId);
            request.setUserId(userId);

            // The deploy status may depend on configuration.
            request.setDeployStatus(DEPLOY_STATUS.PRODUCTION.ordinal());

            request.setMessageType(messageType.ordinal());
            request.setMessage(message);

            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

            return response.getSuccessAmount();

        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }

        return 0;
    }

    /**
     * Push a multicast notification message to a group of devices identified by
     * groupTag
     * 
     * @param groupTag
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushMulticastNotificationMessage(String groupTag, NotificationMessage message) {
        return pushMulticastMessage(groupTag, message.toString(), MESSAGE_TYPE.NOTIFICATION);
    }

    /**
     * Push a multicast in-App message to a group of devices identified by
     * groupTag
     * 
     * @param groupTag
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushMulticastInAppMessage(String groupTag, String message) {
        return pushMulticastMessage(groupTag, message, MESSAGE_TYPE.IN_APP);
    }

    /**
     * Push a multicast message to a group of devices identified by groupTag
     * 
     * @param groupTag
     * @param message
     * @param messageType
     * @param deployStatus
     * @return the number of successful messages pushed
     */
    private int pushMulticastMessage(String groupTag, String message, MESSAGE_TYPE messageType) {
        try {
            PushTagMessageRequest request = new PushTagMessageRequest();

            request.setTagName(groupTag);

            // request.setDeviceType(bindInfo.getDeviceType());
            request.setDeployStatus(DEPLOY_STATUS.PRODUCTION.ordinal());

            request.setMessageType(messageType.ordinal());
            request.setMessage(message.toString());

            PushTagMessageResponse response = channelClient.pushTagMessage(request);

            return response.getSuccessAmount();

        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }

        return 0;
    }

    /**
     * Push a broadcast notification message
     * 
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushBroadcastNotificationMessage(NotificationMessage message) {
        return pushBroadcastMessage(message.toString(), MESSAGE_TYPE.NOTIFICATION);
    }

    /**
     * Push a broadcast in-App message
     * 
     * @param message
     * @return the number of successful messages pushed
     */
    public int pushBroadcastInAppMessage(String message) {
        return pushBroadcastMessage(message, MESSAGE_TYPE.IN_APP);
    }

    /**
     * Push a broadcast message
     * 
     * @param message
     * @param messageType
     * @param deployStatus
     * @return the number of successful messages pushed
     */
    private int pushBroadcastMessage(String message, MESSAGE_TYPE messageType) {
        try {
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();

            // request.setDeviceType(bindInfo.getDeviceType());
            request.setDeployStatus(DEPLOY_STATUS.PRODUCTION.ordinal());

            request.setMessageType(messageType.ordinal());
            request.setMessage(message.toString());

            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);

            return response.getSuccessAmount();

        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }

        return 0;
    }

    /**
     * Set a tag for the user
     * 
     * @param userId
     * @param tag
     */
    public void setUserTag(String userId, String tag) {
        try {
            SetTagRequest request = new SetTagRequest();
            request.setUserId(userId);
            request.setTag(tag);
            channelClient.setTag(request);
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }
    }

    /**
     * Delete a tag from the user
     * 
     * @param userId
     * @param tag
     */
    public void deleteUserTag(String userId, String tag) {
        try {
            DeleteTagRequest request = new DeleteTagRequest();
            request.setUserId(userId);
            request.setTag(tag);
            channelClient.deleteTag(request);
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }
    }

    /**
     * Get a list of tags that the user has.
     * 
     * @param userId
     * @return list of tags
     */
    public List<String> getUserTags(String userId) {
        try {
            List<String> tags = new ArrayList<String>();
            QueryUserTagsRequest request = new QueryUserTagsRequest();
            request.setUserId(userId);
            QueryUserTagsResponse response = channelClient.queryUserTags(request);
            for (TagInfo tag : response.getTags()) {
                tags.add(tag.getName());
            }

            return tags;
        } catch (ChannelClientException e) {
            handleClientException(e);
        } catch (ChannelServerException e) {
            handleServerException(e);
        }

        return null;
    }

    private void handleClientException(ChannelClientException e) {
        // TODO: how to handle client side exceptions for baidu push services
        e.printStackTrace();
    }

    private void handleServerException(ChannelServerException e) {
        // TODO: how to handle server side exceptions for baidu push services
        System.out.println(String.format("request_id: %d, error_code: %d, error_message: %s", e.getRequestId(),
                e.getErrorCode(), e.getErrorMsg()));
    }

}
