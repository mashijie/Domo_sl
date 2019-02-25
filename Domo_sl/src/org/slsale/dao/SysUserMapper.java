package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.SysUser;

public interface SysUserMapper {
	public Integer logoutUser(SysUser sysUser);
	
	public SysUser login(@Param("loginCode") String loginCode,@Param("password") String password);
	

	public Integer updatePwd(SysUser sysUser);
	
	public Integer updateSecodePwd(SysUser sysUser);
	
	public List<SysUser> getAllSysUser(@Param("page")Integer page,@Param("pageSize")Integer pageSize,
			@Param("s_loginCode")String s_loginCode,@Param("s_referCode")String s_referCode,
			@Param("s_roleId")String s_roleId,@Param("s_isStart")String s_isStart);
	
	public Integer addUser(SysUser sysUser);
	
	public SysUser getUserById(@Param("id") String id);
	
	public Integer updateUser(SysUser sysUser);
	
	public Integer deleteUser(@Param("id")String id);
	
	public SysUser getUserByRoleId(@Param("roleId")String roleId);
}
