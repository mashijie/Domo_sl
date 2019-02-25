package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Reply;

public interface ReplyService {

	public List<Reply> getReplyById(String messageId);
	
	public Integer addReply(Reply reply);
	
	public Integer delReplyByMessageId(String messageId);
}
