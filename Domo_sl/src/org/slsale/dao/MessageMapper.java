package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Message;

public interface MessageMapper {

	public List<Message> getMessageByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);
	
	public List<Message> getAllMessage();
	
	public Message getMessageById(@Param("id")String id);
	
	public Integer updateMessageStateById(@Param("id")String id);
	
	public Integer delMessageStateById(@Param("id")String id);
	
	public Integer addMessage(Message message);
}
