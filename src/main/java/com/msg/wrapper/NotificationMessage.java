package com.msg.wrapper;

import java.util.HashMap;
import java.util.Map;

import com.msg.enums.DeviceType;

public class NotificationMessage {

    // required for Android, optional for iOS
    private String title = null;
    private String description = null;

    // Only for Android, optional
    private int notificationBuilderId = -1;
    private int notificationBasicStyle = -1;
    private int openType = -1;
    private int netSupport = -1;
    private int userConfirm = -1;
    private String url = null;
    private String pkgContent = null;
    private String pkgName = null;
    private String pkgVersion = null;

    // Self-defined key-value pairs for Android
    private Map<String, String> customContent = null;

    // Only for iOS, Optional
    private String alert = null;
    private String sound = null;
    private int badge = -1;

    // Self-defined key-value pairs for iOS
    private Map<String, String> iOSContent = null;
    
    private String deviceType = "[3,4]";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotificationBuilderId() {
        return notificationBuilderId;
    }

    public void setNotificationBuilderId(int notificationBuilderId) {
        this.notificationBuilderId = notificationBuilderId;
    }

    public int getNotificationBasicStyle() {
        return notificationBasicStyle;
    }

    public void setNotificationBasicStyle(int notificationBasicStyle) {
        this.notificationBasicStyle = notificationBasicStyle;
    }

    public int getOpenType() {
        return openType;
    }

    public void setOpenType(int openType) {
        this.openType = openType;
    }

    public int getNetSupport() {
        return netSupport;
    }

    public void setNetSupport(int netSupport) {
        this.netSupport = netSupport;
    }

    public int getUserConfirm() {
        return userConfirm;
    }

    public void setUserConfirm(int userConfirm) {
        this.userConfirm = userConfirm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPkgContent() {
        return pkgContent;
    }

    public void setPkgContent(String pkgContent) {
        this.pkgContent = pkgContent;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getPkgVersion() {
        return pkgVersion;
    }

    public void setPkgVersion(String pkgVersion) {
        this.pkgVersion = pkgVersion;
    }

    public Map<String, String> getCustomeContent() {
        return customContent;
    }

    public void setCustomeContent(Map<String, String> customeContent) {
        this.customContent = customeContent;
    }
    
	public void setDeviceType(DeviceType...deviceType) {
		this.deviceType="[";
		for(DeviceType d:deviceType){
			this.deviceType+=d.ordinal()+",";
		}
		this.deviceType = this.deviceType.substring(0,this.deviceType.length()-1)+"]";
	}

	public void addCustomContent(String key, String value) {
        if (this.customContent == null) {
            this.customContent = new HashMap<String, String>();
        }

        this.customContent.put(key, value);
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public Map<String, String> getIosContent() {
        return iOSContent;
    }

    public void setIosContent(Map<String, String> iOSContent) {
        this.iOSContent = iOSContent;
    }

    public void addIOSContent(String key, String value) {
        if (this.iOSContent == null) {
            this.iOSContent = new HashMap<String, String>();
        }

        this.iOSContent.put(key, value);
    }

    private String appendField(String message, String name, String value) {
        if (value != null && !value.equalsIgnoreCase("")) {
            String comma = "";
            if (message.length() > 1) {
                comma = ",";
            }
            return message + comma + "\"" + name + "\":\"" + value + "\"";
        }

        return message;
    }

    private String appendField(String message, String name, int value) {
        if (value != -1) {
            String comma = "";
            if (message.length() > 1) {
                comma = ",";
            }
            return message + comma + "\"" + name + "\":" + value;
        }

        return message;
    }

    private String appendCustomContent(String message) {
        if (this.customContent != null && this.customContent.size() > 0) {
            String comma = "";
            if (message.length() > 1) {
                comma = ",";
            }
            String msg = message + comma + "\"custom_content\":{";
            comma = "";
            for (Map.Entry<String, String> entry : this.customContent.entrySet()) {
                msg = msg + comma + "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"";
                comma = ",";
            }
            msg += "}";
            return msg;
        } else {
            return message;
        }
    }

    private String appendAPS(String message) {
        if (this.alert != null || this.sound != null || this.badge != -1) {
            String comma = "";
            if (message.length() > 1) {
                comma = ",";
            }
            String msg = message + comma + "\"aps\":{";
            comma = "";
            if (this.alert != null) {
                msg = msg + "\"alert\":\"" + this.alert + "\"";
                comma = ",";
            }
            if (this.sound != null) {
                msg = msg + comma + "\"sound\":\"" + this.sound + "\"";
                comma = ",";
            }
            if (this.badge != -1) {
                msg = msg + comma + "\"badge\":" + this.badge;
            }
            msg += "}";
            return msg;
        } else {
            return message;
        }
    }

    private String appendIOSContent(String message) {
        if (this.iOSContent != null && this.iOSContent.size() > 0) {
            String comma = "";
            if (message.length() > 1) {
                comma = ",";
            }

            String msg = message;
            for (Map.Entry<String, String> entry : this.iOSContent.entrySet()) {
                msg = msg + comma + "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"";
                comma = ",";
            }

            return msg;
        } else {
            return message;
        }
    }

    // generate a message in the required format.
    @Override
    public String toString() {
        String msg = "{";

        msg = appendField(msg, "title", this.title);
        msg = appendField(msg, "description", this.description);
        msg = appendField(msg, "notification_build_id", this.notificationBuilderId);
        msg = appendField(msg, "notification_basic_style", this.notificationBasicStyle);
        msg = appendField(msg, "open_type", this.openType);
        msg = appendField(msg, "net_support", this.netSupport);
        msg = appendField(msg, "user_confirm", this.userConfirm);
        msg = appendField(msg, "url", this.url);
        msg = appendField(msg, "pkg_content", this.pkgContent);
        msg = appendField(msg, "pkg_name", this.pkgName);
        msg = appendField(msg, "pkg_version", this.pkgVersion);        
        msg = appendField(msg, "device_type", this.deviceType);
        
        msg = appendCustomContent(msg);
        msg = appendAPS(msg);
        msg = appendIOSContent(msg);

        msg += "}";

        return msg;
    }

    public static NotificationMessage generateSimpleIOSMessage(String content) {
        NotificationMessage msg = new NotificationMessage();
        msg.setAlert(content);

        return msg;
    }

    public static NotificationMessage generateSimpleAndroidMessage(String title, String content) {
        NotificationMessage msg = new NotificationMessage();
        msg.setTitle(title);
        msg.setDescription(content);

        return msg;
    }

    public static NotificationMessage generateSimpleMessage(String title, String content, String alert) {
        NotificationMessage msg = new NotificationMessage();
        msg.setTitle(title);
        msg.setDescription(content);

        if (alert != null) {
            msg.setAlert(alert);
        }

        return msg;
    }
}
