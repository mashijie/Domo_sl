package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.SysUser;

public interface MemberMapper {
	public List<SysUser> getMemberList(@Param("page")Integer page,@Param("pageSize")Integer pageSize,
			@Param("s_loginCode")String s_loginCode,@Param("s_userName")String s_userName);
	public Integer loginCodeIsExit(@Param("loginCode")String loginCode,@Param("id") String id);
}
