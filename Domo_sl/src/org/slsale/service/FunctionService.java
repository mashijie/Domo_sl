package org.slsale.service;

import java.util.List;

import org.slsale.pojo.Function;

public interface FunctionService {

	public List<Function> getAllFunction();
	
	public List<Function> getSubFuncList(Function function);
	
}
