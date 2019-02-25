package org.slsale.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reply {	
	private String id;
	private String messageId;
	private String replyContent;
	private String createdBy;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Reply [id=" + id + ", messageId=" + messageId + ", replyContent=" + replyContent + ", createdBy="
				+ createdBy + ", createTime=" + createTime + "]";
	}
}
