package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Authority;

public interface AuthoritymanageMapper {

	public List<Authority> getAuthoritys(@Param("rid")String rid);
	
	public Authority getIsHave(@Param("rid")String rid,@Param("fid")String fid);
	
	public Integer delAuthority(@Param("rid") String rid);
	
	public Integer addAuthority(Authority authority);
}
