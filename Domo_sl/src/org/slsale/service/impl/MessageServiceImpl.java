package org.slsale.service.impl;

import java.util.List;

import org.slsale.dao.MessageMapper;
import org.slsale.pojo.Message;
import org.slsale.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageMapper messageMapper;
	
	@Override
	public List<Message> getMessageByPage(Integer page, Integer pageSize) {
		return messageMapper.getMessageByPage(page, pageSize);
	}

	@Override
	public List<Message> getAllMessage() {
		return messageMapper.getAllMessage();
	}

	@Override
	public Message getMessageById(String id) {
		return messageMapper.getMessageById(id);
	}

	@Override
	public Integer updateMessageStateById(String id) {
		return messageMapper.updateMessageStateById(id);
	}

	@Override
	public Integer delMessageStateById(String id) {
		return messageMapper.delMessageStateById(id);
	}

	@Override
	public Integer addMessage(Message message) {
		return messageMapper.addMessage(message);
	}

}
