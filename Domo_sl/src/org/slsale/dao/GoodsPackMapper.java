package org.slsale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.GoodsPack;
import org.springframework.web.bind.annotation.RequestParam;

public interface GoodsPackMapper {
	
	public List<GoodsPack> getGoodsPacks(@Param("page")Integer page,@Param("pageSize")Integer pageSize,
			@Param("s_goodsPackName")String s_goodsPackName,@Param("s_typeId")String s_typeId,
			@Param("s_state")String s_state);
	
	public GoodsPack getGoodsPackById(@Param("id") String id);
	
	public Integer saveModifyGoodsPack(GoodsPack goodsPack);
	
	public Integer addGoodsPack(GoodsPack goodsPack);
	
	public Integer goodsPackcodeIsExit(@Param("goodsPackCode") String goodsPackCode);
	
	public Integer delGoodsPack(@Param("delId") String delId);
}
