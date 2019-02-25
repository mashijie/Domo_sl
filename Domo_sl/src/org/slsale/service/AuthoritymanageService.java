package org.slsale.service;

import java.util.List;

import org.slsale.pojo.Authority;

public interface AuthoritymanageService {

	
	public List<Authority> getAuthoritys(String rid);
	
	public Authority getIsHave(String rid,String fid);

	public Integer delAuthority(String rid);
	public Integer addAuthority(Authority authority);

}
