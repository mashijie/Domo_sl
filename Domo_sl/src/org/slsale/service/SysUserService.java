package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.SysUser;

public interface SysUserService {
	public Integer logoutUser(SysUser sysUser);
	
	public SysUser login(String loginCode,String password);
	
	public Integer updatePwd(SysUser sysUser);
	
	public Integer updateSecodePwd(SysUser sysUser);
	
	public List<SysUser> getAllSysUser(Integer page,Integer pageSize,String s_loginCode,String s_referCode,String s_roleId,String s_isStart);

	public Integer addUser(SysUser sysUser);
	
	public SysUser getUserById(String id);
	
	public Integer updateUser(SysUser sysUser);
	
	public Integer deleteUser(String id);
	
	public SysUser getUserByRoleId(String roleId);
}
