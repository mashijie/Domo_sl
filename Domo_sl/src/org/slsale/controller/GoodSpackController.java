package org.slsale.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.GoodsPack;
import org.slsale.pojo.SysUser;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.GoodsPackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.sj.util.Page;

@Controller
@RequestMapping("/GoodSpack")
public class GoodSpackController {

	@Resource
	GoodsPackService goodsPackService;
	@Resource
	DataDictionaryService dataDictionaryService;
	
	@RequestMapping("/GoodSpackList")
	public String GoodSpackList(HttpServletRequest request,
			@RequestParam(value="s_goodsPackName",required=false)String s_goodsPackName,
			@RequestParam(value="s_typeId",required=false)String s_typeId,
			@RequestParam(value="s_state",required=false)String s_state,
			@RequestParam(value="p",required=false)String p){
		Page page=new Page();
		page.setPageSize(1);
		if (p == null || p.equals("")) {
			page.setPage(1);
		} else {
			page.setPage(Integer.parseInt(p));
		}
		for (GoodsPack goodsPack : goodsPackService.getGoodsPacks((page.getPage()-1)*page.getPageSize(),page.getPageSize(),s_goodsPackName,s_typeId,s_state)) {
			page.getItems().add(goodsPack);
		}
		page.setPageCount(goodsPackService.getGoodsPacks(null, null, s_goodsPackName, s_typeId, s_state).size());
		request.setAttribute("s_goodsPackName", s_goodsPackName);
		request.setAttribute("s_typeId", s_typeId);
		request.setAttribute("s_state", s_state);
		request.setAttribute("page", page);
		request.setAttribute("packTypeList", dataDictionaryService.getDataDictionaryByType("PACK_TYPE"));
		return "backend/goodspacklist";
	}
	
	@RequestMapping("/viewgoodspack")
	public String viewgoodspack(String id,HttpServletRequest request){
		request.setAttribute("goodsPack", goodsPackService.getGoodsPackById(id));
		return "backend/viewgoodspack";
	}
	
	@RequestMapping("/modifygoodspack")
	public String modifygoodspack(String id,HttpServletRequest request){
		request.setAttribute("goodsPack", goodsPackService.getGoodsPackById(id));
		request.setAttribute("packTypeList", dataDictionaryService.getDataDictionaryByType("PACK_TYPE"));
		return "backend/modifygoodspack";
	}
	
	@RequestMapping("/savemodifygoodspack")
	public String savemodifygoodspack(GoodsPack goodsPack){
		goodsPack.setLastUpdateTime(new Date());
		if(goodsPackService.saveModifyGoodsPack(goodsPack)>0){
			System.out.println("修改成功！！");
		}
		return "redirect:GoodSpackList";
	}
	
	@RequestMapping("/addgoodspack")
	public String addgoodspack(HttpServletRequest request){
		request.setAttribute("packTypeList", dataDictionaryService.getDataDictionaryByType("PACK_TYPE"));
		return "backend/addgoodspack";
	}
	
	@RequestMapping("/saveAddGoodsPack")
	public String saveAddGoodsPack(GoodsPack goodsPack,HttpServletRequest request){
		goodsPack.setCreatedBy(((SysUser)request.getSession().getAttribute("user")).getLoginCode());
		goodsPack.setCreateTime(new Date());
		for (DataDictionary dataDictionary : dataDictionaryService.getDataDictionaryByType("PACK_TYPE")) {
			if(dataDictionary.getValueId().equals(goodsPack.getTypeId())){
				goodsPack.setTypeName(dataDictionary.getValueName());
				break;
			}
		}
		if(goodsPackService.addGoodsPack(goodsPack)>0){
			System.out.println("添加成功！！");
		}
		return "redirect:GoodSpackList";
	}

	@RequestMapping("/goodspackcodeisexit")
	@ResponseBody
	public String goodsPackcodeIsExit(String goodsPackCode){
		if(goodsPackService.goodsPackcodeIsExit(goodsPackCode)>0){
			return "repeat";
		}
		return "only";
	}
	
	@RequestMapping("/delgoodspack")
	@ResponseBody
	public String delGoodsPack(String delId){
		if(goodsPackService.delGoodsPack(delId)>0){
			return "success";
		}
		return "faild";
	}
	
	
}
