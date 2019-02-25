package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.SysUserMapper;
import org.slsale.pojo.SysUser;
import org.slsale.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	SysUserMapper sysUserMapper;

	@Override
	public SysUser login(String loginCode, String password) {
		return sysUserMapper.login(loginCode, password);
	}

	@Override
	public Integer updatePwd(SysUser sysUser) {
		return sysUserMapper.updatePwd(sysUser);
	}

	@Override
	public Integer updateSecodePwd(SysUser sysUser) {
		return sysUserMapper.updateSecodePwd(sysUser);
	}

	@Override
	public List<SysUser> getAllSysUser(Integer page, Integer pageSize, String s_loginCode, String s_referCode,
			String s_roleId, String s_isStart) {
		return sysUserMapper.getAllSysUser(page, pageSize, s_loginCode, s_referCode, s_roleId, s_isStart);
	}

	@Override
	public Integer addUser(SysUser sysUser) {

		return sysUserMapper.addUser(sysUser);
	}

	@Override
	public SysUser getUserById(String id) {
		return sysUserMapper.getUserById(id);
	}

	@Override
	public Integer updateUser(SysUser sysUser) {
		return sysUserMapper.updateUser(sysUser);
	}

	@Override
	public Integer deleteUser(String id) {
		return sysUserMapper.deleteUser(id);
	}

	@Override
	public SysUser getUserByRoleId(String roleId) {
		return sysUserMapper.getUserByRoleId(roleId);
	}

	@Override
	public Integer logoutUser(SysUser sysUser) {
		return sysUserMapper.logoutUser(sysUser);
	}
}
