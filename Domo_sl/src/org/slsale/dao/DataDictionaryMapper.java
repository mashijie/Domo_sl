package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.DataDictionary;

public interface DataDictionaryMapper {
	public List<DataDictionary> getDataDictionaryByType(@Param("type")String type);
	
	public List<DataDictionary> getAllDataDictionary();
	
	public Integer addDicSub(DataDictionary dataDictionary);
	
	public Integer getMaxValueId(@Param("type")String type);
	
	public Integer delMainDic(DataDictionary dataDictionary);
	
	public Integer delDic(@Param("id")String id);
	
	public Integer typeCodeIsexit(@Param("typeCode") String typeCode,@Param("id")String id);
	
	public Integer modifyDic(DataDictionary dataDictionary);
	
	public Integer modifylDic(@Param("newTypeCode")String newTypeCode,
			@Param("newTypeName")String newTypeName,
			@Param("oldTypeCode")String oldTypeCode,
			@Param("oldTypeName")String oldTypeName);
}
