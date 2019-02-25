package org.slsale.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Message {
	private String id;
	private String createdBy;
	private String messageCode;
	private String messageTitle;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	private String messageContent;
	private String state;
	private List<Reply> replyList;
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	@Override
	public String toString() {
		return "Message [id=" + id + ", createdBy=" + createdBy + ", messageCode=" + messageCode + ", messageTitle="
				+ messageTitle + ", messageContent=" + messageContent + ", statr=" + state + ", createTime="
				+ createTime + "]";
	}
	
}
