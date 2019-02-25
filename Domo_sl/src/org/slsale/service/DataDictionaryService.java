package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.DataDictionary;

public interface DataDictionaryService {

	public List<DataDictionary> getDataDictionaryByType(String type);

	public List<DataDictionary> getAllDataDictionary();

	public Integer getMaxValueId(String type);

	public Integer addDicSub(DataDictionary dataDictionary);

	public Integer delMainDic(DataDictionary dataDictionary);

	public Integer delDic(String id);

	public Integer typeCodeIsexit(String typeCode, String id);

	public Integer modifyDic(DataDictionary dataDictionary);

	public Integer modifylDic(String newTypeCode, String newTypeName, String oldTypeCode, String oldTypeName);
}
