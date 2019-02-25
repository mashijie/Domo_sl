package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.GoodsPackMapper;
import org.slsale.pojo.GoodsPack;
import org.slsale.service.GoodsPackService;
import org.springframework.stereotype.Service;

@Service
public class GoodsPackServiceImpl implements GoodsPackService{

	@Resource
	GoodsPackMapper goodsPackMapper;
	
	@Override
	public List<GoodsPack> getGoodsPacks(Integer page, Integer pageSize, String s_goodsPackName, String s_typeId,
			String s_state) {
		return goodsPackMapper.getGoodsPacks(page, pageSize, s_goodsPackName, s_typeId, s_state);
	}

	@Override
	public GoodsPack getGoodsPackById(String id) {
		return goodsPackMapper.getGoodsPackById(id);
	}

	@Override
	public Integer saveModifyGoodsPack(GoodsPack goodsPack) {
		return goodsPackMapper.saveModifyGoodsPack(goodsPack);
	}

	@Override
	public Integer addGoodsPack(GoodsPack goodsPack) {
		return goodsPackMapper.addGoodsPack(goodsPack);
	}

	@Override
	public Integer goodsPackcodeIsExit(String goodsPackCode) {
		return goodsPackMapper.goodsPackcodeIsExit(goodsPackCode);
	}

	@Override
	public Integer delGoodsPack(String delId) {
		return goodsPackMapper.delGoodsPack(delId);
	}

}
