package org.slsale.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slsale.pojo.GoodsInfo;
import org.slsale.pojo.SysUser;
import org.slsale.service.GoodsInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import pers.sj.util.Page;

@Controller
@RequestMapping("/GoodsInfo")
public class GoodsInfoController {

	@Resource
	GoodsInfoService goodsInfoService;

	@RequestMapping("/GoodsInfoList")
	public String GoodsInfoList(@RequestParam(value = "p", required = false) String p,
			@RequestParam(value = "s_goodsName", required = false) String s_goodsName,
			@RequestParam(value = "s_state", required = false) String s_state, HttpServletRequest request) {
		Page page = new Page();
		page.setPageSize(1);
		if (p == null || p.equals("")) {
			page.setPage(1);
		} else {
			page.setPage(Integer.parseInt(p));
		}
		for (GoodsInfo goodsInfo : goodsInfoService.getGoodsInfo((page.getPage() - 1) * page.getPageSize(),
				page.getPageSize(), s_goodsName, s_state)) {
			page.getItems().add(goodsInfo);
		}
		if (s_state == null || s_goodsName == null) {
			page.setPageCount(goodsInfoService.getAllGoodsInfo().size() / page.getPageSize());
		} else {
			page.setPageCount(
					goodsInfoService.getGoodsInfo(null, null, s_goodsName, s_state).size() / page.getPageSize());
		}

		request.setAttribute("page", page);
		request.setAttribute("s_goodsName", s_goodsName);
		request.setAttribute("s_state", s_state);
		return "backend/goodsinfolist";
	}

	@RequestMapping("/modifystate")
	@ResponseBody
	public Object modifystate(String state) {
		GoodsInfo goodsInfo = JSONArray.parseObject(state, GoodsInfo.class);
		if (goodsInfoService.updateIsStart(goodsInfo) > 0) {
			return "success";
		} else {
			return "failed";
		}
	}

	@RequestMapping("/getgoodsinfo")
	@ResponseBody
	public Object getgoodsinfo(String id) {
		GoodsInfo goodsInfo = goodsInfoService.getGoodsInfoById(id);
		if (goodsInfo == null) {
			return "nodata";
		}
		return goodsInfo;
	}

	// 重定向失败
	@RequestMapping(value = "/addgoodsinfo", method = RequestMethod.POST)
	public String addgoodsinfo(GoodsInfo goodsInfo, HttpServletRequest request) throws IOException {
		goodsInfo.setCreatedBy(((SysUser) request.getSession().getAttribute("user")).getLoginCode());
		goodsInfo.setCreateTime(new Date());
		if (goodsInfoService.addGoodsInfo(goodsInfo) != null) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
		return "redirect:/GoodsInfo/GoodsInfoList";
	}

	@RequestMapping("/delgoodsinfo")
	@ResponseBody
	public Object delgoodsinfo(String delId) {
		if (goodsInfoService.isExitInPack(delId) > 0) {
			goodsInfoService.delgoodsinfo(delId);
			return "success";
		}
		return "isused";
	}

	@RequestMapping("/modifygoodsinfo")
	public String modifygoodsinfo(GoodsInfo goodsInfo) {
		goodsInfo.setLastUpdateTime(new Date());
		if (goodsInfoService.modifyGoodsInfo(goodsInfo) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败");
		}
		return "redirect:/GoodsInfo/GoodsInfoList";
	}

	@RequestMapping("/goodsSNisexit")
	@ResponseBody
	public Object goodsSNisexit(String goodsSN) {
		if (goodsInfoService.goodsSNisexit(goodsSN) > 0) {
			return "repeat";
		}
		return "only";
	}

}