package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.GoodsInfo;

public interface GoodsInfoService {

	public List<GoodsInfo> getAllGoodsInfo();
	
	public Integer updateIsStart(GoodsInfo goodsInfo);
	
	public List<GoodsInfo> getGoodsInfo(Integer page,Integer pageSize,String s_goodsName,String s_state);

	public GoodsInfo getGoodsInfoById(String id);
	
	public Integer addGoodsInfo(GoodsInfo goodsInfo);
	
	public Integer delgoodsinfo(String delId);
	
	public Integer modifyGoodsInfo(GoodsInfo goodsInfo);
	
	public Integer isExitInPack(String id); 
	
	public Integer goodsSNisexit(String goodsSN); 
}
