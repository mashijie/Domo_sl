package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.SysUser;

public interface MemberService {

	public List<SysUser> getMemberList(Integer page, Integer pageSize, String s_loginCode, String s_userName);

	public Integer loginCodeIsExit(String loginCode,String id);
}
