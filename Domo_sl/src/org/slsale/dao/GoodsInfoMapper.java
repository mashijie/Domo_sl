package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.GoodsInfo;

public interface GoodsInfoMapper {

	public List<GoodsInfo> getAllGoodsInfo();
	
	public Integer updateIsStart(GoodsInfo goodsInfo);
	
	public List<GoodsInfo> getGoodsInfo(@Param("page")Integer page,@Param("pageSize")Integer pageSize,@Param("s_goodsName")String s_goodsName,@Param("s_state")String s_state);

	public GoodsInfo getGoodsInfoById(@Param("id") String id);
	
	public Integer addGoodsInfo(GoodsInfo goodsInfo);
	
	public Integer delgoodsinfo(@Param("id") String delId);

	public Integer modifyGoodsInfo(GoodsInfo goodsInfo);
	
	public Integer isExitInPack(@Param("id") String id);
	
	public Integer goodsSNisexit(@Param("goodsSN") String goodsSN); 
}