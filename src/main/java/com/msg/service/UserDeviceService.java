package com.msg.service;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.msg.domain.UserDevice;
import com.msg.event.BindDeviceEvent;

@Validated
public interface UserDeviceService extends BaseService<UserDevice>{
	void bindUserDevice(@NotNull BindDeviceEvent event);
}
