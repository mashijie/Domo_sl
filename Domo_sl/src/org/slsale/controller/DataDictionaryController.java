package org.slsale.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slsale.pojo.DataDictionary;
import org.slsale.service.DataDictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("Dictionary")
public class DataDictionaryController {

	@Resource
	DataDictionaryService dataDictionaryService;
	
	@RequestMapping("/dicmanage")
	public String dicmanage(HttpServletRequest request){
		request.setAttribute("dataList", dataDictionaryService.getAllDataDictionary());
		return "backend/dicmanage";
	}
	
	@RequestMapping("getJsonDic")
	@ResponseBody
	public Object getJsonDic(String tcode){
		List<DataDictionary> dataDictionary=dataDictionaryService.getDataDictionaryByType(tcode);
		if(dataDictionary==null){
			return "failed";
		}
		return dataDictionary;
	}
	
	@RequestMapping("/addDicSub")
	@ResponseBody
	public Object addDicSub(String dic){
		DataDictionary dataDictionary=JSONArray.parseObject(dic,DataDictionary.class);
		dataDictionary.setValueId(dataDictionaryService.getMaxValueId(dataDictionary.getTypeCode())+1);
		HashMap<String, Object> result=new HashMap<String, Object>();
		for (DataDictionary dataDictionarys : dataDictionaryService.getDataDictionaryByType(dataDictionary.getTypeCode())) {
			if(dataDictionarys.getValueName().equals(dataDictionary.getValueName())){
				result.put("result", "rename");
				return result;
			}
		}
		if(dataDictionaryService.addDicSub(dataDictionary)>0){
			result.put("result", "success");
		}else{
			result.put("result", "failed");
		}
		return result;
	}
	
	@RequestMapping("/addDic")
	@ResponseBody
	public Object addDic(String dic){
		DataDictionary dataDictionary=JSONArray.parseObject(dic,DataDictionary.class);
		for (DataDictionary dataDictionarys : dataDictionaryService.getAllDataDictionary()) {
			if(dataDictionarys.getTypeCode().equals(dataDictionary.getTypeCode())){
				return "rename";
			}
		}
		if(dataDictionaryService.addDicSub(dataDictionary)>0){
			return "success";
		}else{
			return "failed";
		}
	}
	
	@RequestMapping("/delMainDic")
	@ResponseBody
	public Object delMainDic(String dic){
		DataDictionary dataDictionary=JSONArray.parseObject(dic,DataDictionary.class);
		if(dataDictionaryService.delMainDic(dataDictionary)>0){
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping("/delDic")
	@ResponseBody
	public Object delDic(String id){
		if(dataDictionaryService.delDic(id)>0){
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping("/typecodeisexit")
	@ResponseBody
	public Object typeCodeIsexit(String typeCode,String id){
		if(dataDictionaryService.typeCodeIsexit(typeCode, id)>0){
			return "repeat";
		}
		return "only";
	}
	//修改子节点
	@RequestMapping("/modifyDic")
	@ResponseBody
	public Object modifyDic(String dicJson){
		DataDictionary dataDictionary=JSONArray.parseObject(dicJson,DataDictionary.class);
		if(dataDictionaryService.modifyDic(dataDictionary)>0){
			return "success";
		}
		return "failed";
	}
	//修改主题
	@RequestMapping("/modifylDic")
	@ResponseBody
	public Object modifylDic(String olddic,String newdic){
		DataDictionary oldDataDictionary=JSONArray.parseObject(olddic,DataDictionary.class);
		DataDictionary newDataDictionary=JSONArray.parseObject(newdic,DataDictionary.class);
		if(dataDictionaryService.modifylDic(newDataDictionary.getTypeCode(), newDataDictionary.getTypeName(), oldDataDictionary.getTypeCode(), oldDataDictionary.getTypeName())>0){
			return "success";
		}
		return "failed";
	}
}
