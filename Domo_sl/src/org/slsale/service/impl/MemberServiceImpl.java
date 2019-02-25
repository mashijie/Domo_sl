package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.MemberMapper;
import org.slsale.pojo.SysUser;
import org.slsale.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

	@Resource
	MemberMapper memberMapper;
	
	
	@Override
	public List<SysUser> getMemberList(Integer page, Integer pageSize, String s_loginCode, String s_userName) {
		return memberMapper.getMemberList(page, pageSize, s_loginCode, s_userName);
	}


	@Override
	public Integer loginCodeIsExit(String loginCode, String id) {
		return memberMapper.loginCodeIsExit(loginCode, id);
	}

}
