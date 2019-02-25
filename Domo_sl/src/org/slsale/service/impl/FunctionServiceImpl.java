package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.FunctionMapper;
import org.slsale.pojo.Function;
import org.slsale.service.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImpl implements FunctionService{

	@Resource
	FunctionMapper functionMapper;
	
	@Override
	public List<Function> getAllFunction() {
		return functionMapper.getAllFunction();
	}

	@Override
	public List<Function> getSubFuncList(Function function) {
		// TODO Auto-generated method stub
		return functionMapper.getSubFuncList(function);
	}

}
