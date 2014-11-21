package com.msg.repo;

import com.msg.domain.Message;
import com.msg.domain.MessageLog;

public interface MessageLogRepo extends BaseRepo<MessageLog>{

	MessageLog findByRecIdAndMessage(Long recId, Message msg);

}
