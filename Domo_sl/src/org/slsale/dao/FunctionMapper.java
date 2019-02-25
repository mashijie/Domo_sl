package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Affiche;
import org.slsale.pojo.Function;

public interface FunctionMapper {

	public List<Function> getAllFunction();
	
	public List<Function> getSubFuncList(Function function);
	
}



//dao  数据库增删改差
//service    调用Dao曾放哪广发
//controller 前端和后台的连接