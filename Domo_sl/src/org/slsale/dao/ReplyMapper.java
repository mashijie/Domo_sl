package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Reply;

public interface ReplyMapper {

	public List<Reply> getReplyById(@Param("messageId")String messageId);
	
	public Integer addReply(Reply reply);
	
	public Integer delReplyByMessageId(@Param("messageId")String messageId);
}
