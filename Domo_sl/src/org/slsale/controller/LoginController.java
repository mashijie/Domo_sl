package org.slsale.controller;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slsale.pojo.SysUser;
import org.slsale.service.AfficheService;
import org.slsale.service.FunctionService;
import org.slsale.service.InformationService;
import org.slsale.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Resource
	SysUserService sysUserService;
	@Resource
	AfficheService afficheService;
	@Resource
	InformationService informationService;
	@Resource
	FunctionService functionService;
	
	
//pojo  sysUser
//dao   sysUser	
//service sysUser
//controller  login
	
	//实现登录方法   
	@RequestMapping("/login")
	@ResponseBody  //处理ajax请求
	public Object login(SysUser user,HttpServletRequest request){  //用sysUser来接收账户和密码
		HashMap<String, String> result=new HashMap<String,String>();    //存储结果    键值对形式
		SysUser sysUser=sysUserService.login(user.getLoginCode(), user.getPassword()); //获取登录的帐号和密码
		if(sysUser!=null){  //从数据库查找有没有这条数据
			request.getSession().setAttribute("user", sysUser);   //放到web的session内置对象里面，方便在以后操作中使用该对象
			request.getSession().setAttribute("afficheList", afficheService.getAllAffiche());
			request.getSession().setAttribute("infoList", informationService.getAllInformation());
			request.getSession().setAttribute("functionList", functionService.getAllFunction());
			result.put("result", "success");   //返回success
			return result;
		}
		result.put("result", "pwderror");
		return result;
	}
	
	//实现注销方法
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		SysUser sysUser=(SysUser)request.getSession().getAttribute("user");   
		sysUser.setLastLoginTime(new Date());  //最后登录时间
		sysUserService.logoutUser(sysUser);
		request.getSession().removeAttribute("user");//删除掉web的session存储的sysuser对象
		return "index";
	}
	
	//实现跳转主页方法
	@RequestMapping("/main")
	public String main(HttpServletRequest request){
		return "main";
	}
}
