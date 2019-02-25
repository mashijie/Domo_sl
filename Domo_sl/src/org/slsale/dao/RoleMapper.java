package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Role;

public interface RoleMapper {

	public List<Role> getAllRole();
	
	public Integer updateRoleIsStart(Role role);
	
	public Integer addRole(Role role);
	
	public Integer delRole(Role role);
	
	public Integer modifyRole(Role role);
}
