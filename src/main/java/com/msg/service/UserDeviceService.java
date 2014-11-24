package com.msg.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.msg.domain.UserDevice;
import com.msg.event.BindDeviceEvent;

@Validated
public interface UserDeviceService extends BaseService<UserDevice>{
	void bindUserDevice(@Valid BindDeviceEvent event);
}
