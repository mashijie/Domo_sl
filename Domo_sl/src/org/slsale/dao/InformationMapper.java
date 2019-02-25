package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Information;

public interface InformationMapper {

	public List<Information> getAllInformation();
	
	public Information getInformationById(@Param("id") String id);
	
	public List<Information> getInformationByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize,@Param("downLoad") String downLoad);
	
	public Integer addInformation(Information information);
	
	public Integer delInformationById(@Param("id") String id);
	
	public Integer modifyInfoState(@Param("id") String id,@Param("state") String state);
	
	public Integer modifyInformation(Information information);
}
