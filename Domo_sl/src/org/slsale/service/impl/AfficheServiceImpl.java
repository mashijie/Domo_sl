package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.AfficheMapper;
import org.slsale.pojo.Affiche;
import org.slsale.service.AfficheService;
import org.springframework.stereotype.Service;

@Service
public class AfficheServiceImpl implements AfficheService{

	@Resource
	AfficheMapper afficheMapper;

	@Override
	public List<Affiche> getAllAffiche() {
		return afficheMapper.getAllAffiche();
	}

	@Override
	public Affiche getAfficheById(String id) {
		return afficheMapper.getAfficheById(id);
	}

	@Override
	public List<Affiche> getAfficheByPage(Integer page, Integer pageSize) {
		return afficheMapper.getAfficheByPage(page, pageSize);
	}

	@Override
	public Integer updateAffiche(Affiche affiche) {
		return afficheMapper.updateAffiche(affiche);
	}

	@Override
	public Integer addAffiche(Affiche affiche) {
		return afficheMapper.addAffiche(affiche);
	}

	@Override
	public Integer delAffiche(String id) {
		return afficheMapper.delAffiche(id);
	}
	
	

}
