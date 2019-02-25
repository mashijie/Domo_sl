package org.slsale.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.RoleFunctions;
import org.slsale.pojo.SysUser;
import org.slsale.service.AuthoritymanageService;
import org.slsale.service.FunctionService;
import org.slsale.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/authoritymanage")
public class AuthoritymanageController {

	@Resource
	RoleService roleService;
	@Resource
	AuthoritymanageService authoritymanageService;
	@Resource
	FunctionService functionService;
	
	@RequestMapping("/authoritymanage")
	public String authoritymanage(HttpServletRequest request){
		request.setAttribute("roleList", roleService.getAllRole());
		return "backend/authoritymanage";
	}
	
	
	@RequestMapping("/functions")
	@ResponseBody
	public Object functions(String rid){
		Function function = new Function();
		function.setId(0);
		//先获取一级管理
		List<Function> fList = functionService.getSubFuncList(function);
		//存储数据
		List<RoleFunctions> rList = new ArrayList<RoleFunctions>();
		if(fList!=null){
			for (Function function2 : fList) {
				RoleFunctions rFunctions = new RoleFunctions();
				rFunctions.setMainFunction(function2);
				rFunctions.setSubFunctions(functionService.getSubFuncList(function2));
				rList.add(rFunctions);
			}
		}
		return rList;
	}
	
	@RequestMapping("/getAuthorityDefault")
	@ResponseBody
	public Object getAuthorityDefault(String rid,String fid){
		if(authoritymanageService.getIsHave(rid, fid)!=null){
			return "success";
		}
		return "没有该数据~框框不能选中";
	}
	
	@RequestMapping("/modifyAuthority")
	@ResponseBody
	public Object modifyAuthority(String ids,HttpServletRequest request){
		String[] arr=ids.split("-");
		String rid=arr[0];
		authoritymanageService.delAuthority(rid);
		SysUser user=(SysUser) request.getSession().getAttribute("user");
		for (int i = 1; i < arr.length; i++) {
			Authority authority=new Authority();
			authority.setCreatedBy(user.getLoginCode());
			authority.setCreationTime(new Date());
			authority.setRoleId(Integer.parseInt(rid));
			authority.setFunctionId(Integer.parseInt(arr[i]));
			authoritymanageService.addAuthority(authority);
		}
		return "success";
	}
	
	
}
