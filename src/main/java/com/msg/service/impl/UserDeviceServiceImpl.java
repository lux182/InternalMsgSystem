package com.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.msg.controller.event.BindDeviceEvent;
import com.msg.domain.UserDevice;
import com.msg.repo.UserDeviceRepo;
import com.msg.service.UserDeviceService;

@Service
public class UserDeviceServiceImpl extends BaseServiceImpl<UserDevice> implements UserDeviceService{

	@Resource
	UserDeviceRepo userDeviceRepo;
	
	@Override
	public void bindUserDevice(BindDeviceEvent event) {
		UserDevice device = userDeviceRepo.findByUserIdAndChannelId(event.getUserId(),event.getChannelId());
		if(device==null){
			device = userDeviceRepo.findByAid(event.getAid());
		}else{
			if(device.getAid()!=event.getAid()){
				userDeviceRepo.delete(device);
				device = userDeviceRepo.findByAid(event.getAid());
			}
		}
		
		if(device==null){
			device = new UserDevice();
			device.setAid(event.getAid());
		}
		device.setChannelId(event.getChannelId());
		device.setUserId(event.getUserId());
		
		userDeviceRepo.save(device);
		
	}

}
