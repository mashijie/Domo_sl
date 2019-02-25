package org.slsale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.GoodsInfoMapper;
import org.slsale.pojo.GoodsInfo;
import org.slsale.service.GoodsInfoService;
import org.springframework.stereotype.Service;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService{

	@Resource
	GoodsInfoMapper goodsInfoMapper;
	
	@Override
	public List<GoodsInfo> getAllGoodsInfo() {
		return goodsInfoMapper.getAllGoodsInfo();
	}

	@Override
	public Integer updateIsStart(GoodsInfo goodsInfo) {
		return goodsInfoMapper.updateIsStart(goodsInfo);
	}

	@Override
	public List<GoodsInfo> getGoodsInfo(Integer page, Integer pageSize, String s_goodsName, String s_state) {
		return goodsInfoMapper.getGoodsInfo(page, pageSize, s_goodsName, s_state);
	}

	@Override
	public GoodsInfo getGoodsInfoById(String id) {
		return goodsInfoMapper.getGoodsInfoById(id);
	}

	@Override
	public Integer addGoodsInfo(GoodsInfo goodsInfo) {
		return goodsInfoMapper.addGoodsInfo(goodsInfo);
	}

	@Override
	public Integer delgoodsinfo(String delId) {
		return goodsInfoMapper.delgoodsinfo(delId);
	}

	@Override
	public Integer modifyGoodsInfo(GoodsInfo goodsInfo) {
		return goodsInfoMapper.modifyGoodsInfo(goodsInfo);
	}

	@Override
	public Integer isExitInPack(String id) {
		return goodsInfoMapper.isExitInPack(id);
	}

	@Override
	public Integer goodsSNisexit(String goodsSN) {
		return goodsInfoMapper.goodsSNisexit(goodsSN);
	}

}
