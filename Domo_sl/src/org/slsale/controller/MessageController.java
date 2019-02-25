package org.slsale.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slsale.pojo.Information;
import org.slsale.pojo.Message;
import org.slsale.pojo.Reply;
import org.slsale.pojo.SysUser;
import org.slsale.service.MessageService;
import org.slsale.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.sj.util.Page;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	MessageService messageService;
	@Autowired
	ReplyService replyService;
	
	@RequestMapping("/messagelist")
	public String messagelist(@RequestParam(value="p",required=false) String p,
			HttpServletRequest request){
		Page page=new Page();
		if(p==null || p.equals("")){
			page.setPage(1);
		}else{
			page.setPage(Integer.parseInt(p));
		}
		page.setPageSize(3);
		for (Message message : messageService.getMessageByPage((page.getPage()-1)*page.getPageSize(), page.getPageSize())) {
			page.getItems().add(message);
		}
		page.setPageCount(messageService.getAllMessage().size()%page.getPageSize()==0?messageService.getAllMessage().size()/page.getPageSize():messageService.getAllMessage().size()/page.getPageSize()+1);
		request.setAttribute("page", page);
		return "message/messagelist";
	}
	
	@RequestMapping("/getmessage")
	@ResponseBody
	public Object getMessage(String id){
		Message message=messageService.getMessageById(id);
		if(message!=null){
			message.setReplyList(replyService.getReplyById(message.getId()));
			return message;
		}else{
			return "failed";
		}
	}
	
	@RequestMapping("/replymessage")
	public String replymessage(Reply reply,HttpServletRequest request){
		SysUser sysUser=(SysUser) request.getSession().getAttribute("user");
		reply.setCreatedBy(sysUser.getLoginCode());
		reply.setCreateTime(new Date());
		replyService.addReply(reply);
		messageService.updateMessageStateById(reply.getMessageId());
		return "redirect:messagelist";
	}
	
	@RequestMapping("/delmessage")
	@ResponseBody
	public String delmessage(String delId){
		System.out.println(delId+"---------------");
		replyService.delReplyByMessageId(delId);
		if(messageService.delMessageStateById(delId)>0){
			return "success";
		}
		return "faild";
	}
	
	@RequestMapping("/mymessage")
	public String mymessage(@RequestParam(value="p",required=false) String p,
			HttpServletRequest request){
		Page page=new Page();
		if(p==null || p.equals("")){
			page.setPage(1);
		}else{
			page.setPage(Integer.parseInt(p));
		}
		page.setPageSize(3);
		for (Message message : messageService.getMessageByPage((page.getPage()-1)*page.getPageSize(), page.getPageSize())) {
			page.getItems().add(message);
		}
		page.setPageCount(messageService.getAllMessage().size()%page.getPageSize()==0?messageService.getAllMessage().size()/page.getPageSize():messageService.getAllMessage().size()/page.getPageSize()+1);
		request.setAttribute("page", page);
		return "message/mymessage";
	}
	
	@RequestMapping("/reply")
	@ResponseBody
	public Object Reply(String id){
		return replyService.getReplyById(id);
	}
	
	
	@RequestMapping("/addmessage")
	public String addmessage(Message message,HttpServletRequest request){
		SysUser sysUser=(SysUser) request.getSession().getAttribute("user");
		message.setCreatedBy(sysUser.getLoginCode());
		message.setCreateTime(new Date());
		message.setMessageTitle(message.getMessageContent());
		message.setMessageCode(message.getMessageContent());
		messageService.addMessage(message);
		return "redirect:mymessage";
	}
}
