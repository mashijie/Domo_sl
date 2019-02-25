package org.slsale.controller;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slsale.pojo.Role;
import org.slsale.pojo.SysUser;
import org.slsale.service.RoleService;
import org.slsale.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	RoleService roleService;
	@Resource
	SysUserService sysUserService;

	@RequestMapping("/roleList")
	public String roleList(HttpServletRequest request) {
		request.setAttribute("roleList", roleService.getAllRole());
		return "backend/rolelist";
	}

	@RequestMapping("/modifyIsStart")
	@ResponseBody
	public Object modifyIsStart(String role) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Role newRole = JSONArray.parseObject(role, Role.class);
		if (roleService.updateRoleIsStart(newRole) > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "failed");
		}
		return JSONArray.toJSON(result);
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public Object addRole(String role, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Role newRole = JSONArray.parseObject(role, Role.class);
		newRole.setCreatedBy(((SysUser) request.getSession().getAttribute("user")).getLoginCode());
		newRole.setCreateDate(new Date());
		newRole.setIsStart(1);
		if (roleService.addRole(newRole) > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "failed");
		}
		return JSONArray.toJSON(result);
	}

	@RequestMapping("/delRole")
	@ResponseBody
	public Object delRole(String role, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Role newRole = JSONArray.parseObject(role, Role.class);
		SysUser sysUser = sysUserService.getUserByRoleId(newRole.getId().toString());
		if (sysUser != null) {
			result.put("result", sysUser.getLoginCode());
		} else {
			if (roleService.delRole(newRole) > 0) {
				result.put("result", "success");
			} else {
				result.put("result", "failed");
			}
		}
		return JSONArray.toJSON(result);
	}

	@RequestMapping("/modifyRole")
	@ResponseBody
	public Object modifyRole(String role) {
		Role newRole = JSONArray.parseObject(role, Role.class);
		if (roleService.modifyRole(newRole) > 0) {
			return "success";
		} else {
			return "failed";
		}
	}

}
