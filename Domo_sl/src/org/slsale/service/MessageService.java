package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Message;

public interface MessageService {
	public List<Message> getMessageByPage(Integer page,Integer pageSize);
	
	public List<Message> getAllMessage();
	
	public Message getMessageById(String id);
	
	public Integer updateMessageStateById(String id);
	
	public Integer delMessageStateById(String id);
	
	public Integer addMessage(Message message);
}
