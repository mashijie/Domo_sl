package org.slsale.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.GoodsPack;

public interface GoodsPackService {

	public List<GoodsPack> getGoodsPacks(Integer page, Integer pageSize, String s_goodsPackName, String s_typeId,
			String s_state);
	
	public GoodsPack getGoodsPackById(String id);
	
	public Integer saveModifyGoodsPack(GoodsPack goodsPack);
	
	public Integer addGoodsPack(GoodsPack goodsPack);
	
	public Integer goodsPackcodeIsExit(String goodsPackCode);
	
	public Integer delGoodsPack(String delId);
}
