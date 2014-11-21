package com.msg.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msg.domain.Message;
import com.msg.enums.MessageStatus;
import com.msg.enums.MessageType;

public interface MessageRepo extends BaseRepo<Message>{

	@Query("from Message msg left join msg.messageLog msglog where (msglog.recId is null or msglog.recId=:recId) and (msglog.status is null or msglog.status=:status) and (msg.indate is null or msg.indate >=:now) and (:type is null or msg.type=:type)")
	Page<Object> findUnreadMsg(Pageable page,@Param("recId") Long recId,@Param("status") MessageStatus status,@Param("type") MessageType type,@Param("now") Date now);
	
	@Query("from Message msg left join msg.messageLog msglog where msglog.recId=:recId and msglog.status=:status and (:type is null or msg.type=:type)")
	Page<Object> findReadMsg(Pageable page,@Param("recId") Long recId,@Param("status") MessageStatus status,@Param("type") MessageType type);

	@Query("from Message msg left join msg.messageLog msglog where (msglog.recId is null or msglog.recId=:recId) and (:type is null or msg.type=:type)")
	Page<Object> findAllMsg(Pageable page, @Param("recId") Long recId, @Param("type") MessageType type);

}
