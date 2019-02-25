package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.InformationMapper;
import org.slsale.pojo.Information;
import org.slsale.service.InformationService;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformationService{

	@Resource
	InformationMapper informationMapper;
	
	@Override
	public List<Information> getAllInformation() {
		return informationMapper.getAllInformation();
	}

	@Override
	public Information getInformationById(String id) {
		return informationMapper.getInformationById(id);
	}

	@Override
	public List<Information> getInformationByPage(Integer page, Integer pageSize,String downLoad) {
		return informationMapper.getInformationByPage(page, pageSize,downLoad);
	}

	@Override
	public Integer addInformation(Information information) {
		return informationMapper.addInformation(information);
	}

	@Override
	public Integer delInformationById(String id) {
		return informationMapper.delInformationById(id);
	}

	@Override
	public Integer modifyInfoState(String id, String state) {
		return informationMapper.modifyInfoState(id, state);
	}

	@Override
	public Integer modifyInformation(Information information) {
		return informationMapper.modifyInformation(information);
	}

}
