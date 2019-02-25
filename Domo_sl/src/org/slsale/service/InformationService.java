package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Information;

public interface InformationService {
	public List<Information> getAllInformation();
	
	public Information getInformationById(String id);
	
	public List<Information> getInformationByPage(Integer page,Integer pageSize,String downLoad);
	
	public Integer addInformation(Information information);
	
	public Integer delInformationById(String id);
	
	public Integer modifyInfoState(String id,String state);
	
	public Integer modifyInformation(Information information);
}
