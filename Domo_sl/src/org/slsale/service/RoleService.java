package org.slsale.service;

import java.util.List;

import org.slsale.pojo.Role;

public interface RoleService {

	public List<Role> getAllRole();
	
	public Integer updateRoleIsStart(Role role);
	
	public Integer addRole(Role role);
	
	public Integer delRole(Role role);
	
	public Integer modifyRole(Role role);
	
}
