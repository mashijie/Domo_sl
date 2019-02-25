package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.RoleMapper;
import org.slsale.pojo.Role;
import org.slsale.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	RoleMapper roleMapper;
	
	@Override
	public List<Role> getAllRole() {
		return roleMapper.getAllRole();
	}

	@Override
	public Integer updateRoleIsStart(Role role) {
		return roleMapper.updateRoleIsStart(role);
	}

	@Override
	public Integer addRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.addRole(role);
	}

	@Override
	public Integer delRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.delRole(role);
	}

	@Override
	public Integer modifyRole(Role role) {
		return roleMapper.modifyRole(role);
	}

	
	
	
	
}
