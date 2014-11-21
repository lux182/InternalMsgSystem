package com.msg.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.msg.controller.event.BindDeviceEvent;
import com.msg.domain.UserDevice;

@Validated
public interface UserDeviceService extends BaseService<UserDevice>{
	void bindUserDevice(@Valid BindDeviceEvent event);
}
