package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.AuthoritymanageMapper;
import org.slsale.pojo.Authority;
import org.slsale.service.AuthoritymanageService;
import org.springframework.stereotype.Service;

@Service
public class AuthoritymanageServiceImpl implements AuthoritymanageService{

	
	@Resource
	AuthoritymanageMapper authoritymanageMapper;
	
	@Override
	public List<Authority> getAuthoritys(String rid) {
		return authoritymanageMapper.getAuthoritys(rid);
	}

	@Override
	public Authority getIsHave(String rid, String fid) {
		return authoritymanageMapper.getIsHave(rid, fid);
	}

	@Override
	public Integer delAuthority(String rid) {
		return authoritymanageMapper.delAuthority(rid);
	}

	@Override
	public Integer addAuthority(Authority authority) {
		return authoritymanageMapper.addAuthority(authority);
	}

}
