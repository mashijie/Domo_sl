package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.ReplyMapper;
import org.slsale.pojo.Reply;
import org.slsale.service.ReplyService;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Resource
	ReplyMapper replyMapper;
	@Override
	public List<Reply> getReplyById(String messageId) {
		return replyMapper.getReplyById(messageId);
	}
	@Override
	public Integer addReply(Reply reply) {
		return replyMapper.addReply(reply);
	}
	@Override
	public Integer delReplyByMessageId(String messageId) {
		return replyMapper.delReplyByMessageId(messageId);
	}

}
