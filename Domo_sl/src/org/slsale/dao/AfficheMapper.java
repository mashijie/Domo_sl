package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Affiche;

public interface AfficheMapper {

	public List<Affiche> getAllAffiche();
	
	public Affiche getAfficheById(@Param("id") String id);
	
	public List<Affiche> getAfficheByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

	public Integer updateAffiche(Affiche affiche);
	
	public Integer addAffiche(Affiche affiche);
	
	public Integer delAffiche(@Param("id") String id);
}
