package org.slsale.pojo;

import java.util.List;

import org.slsale.pojo.Function;


public class RoleFunctions {
	private Function mainFunction;//父节点
	private List<Function> subFunctions;//子节点
	public Function getMainFunction() {
		return mainFunction;
	}
	public void setMainFunction(Function mainFunction) {
		this.mainFunction = mainFunction;
	}
	public List<Function> getSubFunctions() {
		return subFunctions;
	}
	public void setSubFunctions(List<Function> subFunctions) {
		this.subFunctions = subFunctions;
	}
}
