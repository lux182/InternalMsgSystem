package com.msg.repo;

import com.msg.domain.UserDevice;

public interface UserDeviceRepo extends BaseRepo<UserDevice>{

	UserDevice findByUserIdAndChannelId(String userId, Long channelId);

	UserDevice findByAid(Long aid);

}
