package com.msg.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msg.domain.Message;
import com.msg.enums.MessageStatus;

public interface MessageRepo extends BaseRepo<Message>{

	@Query("from Message msg left join msg.messageLog msglog where (msglog.recId is null or msglog.recId=:recId) and (msglog.status is null or msglog.status=:status) and (msg.indate is null or msg.indate >=:now)")
	Page<Object> findUnreadMsg(Pageable page,@Param("recId") Long recId,@Param("status") MessageStatus status,@Param("now") Date now);

	@Query("from Message msg left join msg.messageLog msglog where (msglog.recId is null or msglog.recId=:recId) and msglog.status=:status and (msg.indate is null or msg.indate >=:now)")
	Page<Object> findReadMsg(Pageable page,@Param("recId") Long recId,@Param("status") MessageStatus status,@Param("now") Date now);

}
