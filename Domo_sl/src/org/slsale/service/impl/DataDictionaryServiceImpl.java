package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.DataDictionaryMapper;
import org.slsale.pojo.DataDictionary;
import org.slsale.service.DataDictionaryService;
import org.springframework.stereotype.Service;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService{

	@Resource
	DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	public List<DataDictionary> getDataDictionaryByType(String type) {
		return dataDictionaryMapper.getDataDictionaryByType(type);
	}

	@Override
	public List<DataDictionary> getAllDataDictionary() {
		return dataDictionaryMapper.getAllDataDictionary();
	}

	@Override
	public Integer getMaxValueId(String type) {
		return dataDictionaryMapper.getMaxValueId(type);
	}

	@Override
	public Integer addDicSub(DataDictionary dataDictionary) {
		return dataDictionaryMapper.addDicSub(dataDictionary);
	}

	@Override
	public Integer delMainDic(DataDictionary dataDictionary) {
		return dataDictionaryMapper.delMainDic(dataDictionary);
	}

	@Override
	public Integer delDic(String id) {
		return dataDictionaryMapper.delDic(id);
	}

	@Override
	public Integer typeCodeIsexit(String typeCode, String id) {
		return dataDictionaryMapper.typeCodeIsexit(typeCode, id);
	}

	@Override
	public Integer modifyDic(DataDictionary dataDictionary) {
		return dataDictionaryMapper.modifyDic(dataDictionary);
	}

	@Override
	public Integer modifylDic(String newTypeCode, String newTypeName, String oldTypeCode, String oldTypeName) {
		return dataDictionaryMapper.modifylDic(newTypeCode, newTypeName, oldTypeCode, oldTypeName);
	}

	

}
