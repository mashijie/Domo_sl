package org.slsale.service;

import java.util.List;

import org.slsale.pojo.Affiche;

public interface AfficheService {

	public List<Affiche> getAllAffiche();
	
	public Affiche getAfficheById(String id);
	
	public List<Affiche> getAfficheByPage(Integer page,Integer pageSize);
	
	public Integer updateAffiche(Affiche affiche);
	
	public Integer addAffiche(Affiche affiche);
	
	public Integer delAffiche(String id);
	
}
