package org.slsale.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.slsale.pojo.Affiche;
import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.Information;
import org.slsale.pojo.Role;
import org.slsale.pojo.SysUser;
import org.slsale.service.AfficheService;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.InformationService;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import pers.sj.util.Page;

@Controller
@RequestMapping("/informanage")
public class InformanageController {

	@Resource
	AfficheService afficheService;
	@Resource
	InformationService informationService;
	@Resource
	DataDictionaryService dataDictionaryService;
	
//---Affiche---start
	@RequestMapping("/affiche")
	public String affiche(@RequestParam(value="p",required=false)String p,HttpServletRequest request){
		Page page=new Page();
		if(p==null || p.equals("")){
			page.setPage(1);
		}else{
			page.setPage(Integer.parseInt(p));
		}
		page.setPageSize(3);
		for (Affiche affiche : afficheService.getAfficheByPage((page.getPage()-1)*page.getPageSize(), page.getPageSize())) {
			page.getItems().add(affiche);
		}
		page.setPageCount(afficheService.getAllAffiche().size()%page.getPageSize()==0?afficheService.getAllAffiche().size()/page.getPageSize():afficheService.getAllAffiche().size()/page.getPageSize()+1);
		request.setAttribute("page", page);
		return "informanage/affiche";
	} 
	
	@RequestMapping("/viewAffiche")
	@ResponseBody
	public Object viewAffiche(String id){
		Affiche affiche=afficheService.getAfficheById(id);
		if(affiche==null){
			return "nodata";
		}
		return affiche;
	}
					
	@RequestMapping("/modifyAffiche")
	public String modifyAffiche(Affiche affiche){
		affiche.setStartTime(null);
		affiche.setEndTime(null);
		if(afficheService.updateAffiche(affiche)>0){
			System.out.println("修改成功！");
		}
		return "redirect:affiche";
	}
	
	@RequestMapping("/delAffiche")
	@ResponseBody
	public Object delAffiche(String id){
		if(afficheService.delAffiche(id)>0){
			System.out.println("修改成功！");
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping("/addAffiche")
	public String addAffiche(Affiche affiche,HttpServletRequest request){
		SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
		affiche.setPublisher(sysUser.getLoginCode());
		affiche.setPublishTime(new Date());
		if(afficheService.addAffiche(affiche)>0){
			System.out.println("添加成功！");
		}
		return "redirect:affiche";
	}
//---Affiche---stop
	
	
	
	
	
//---Information---start
	@RequestMapping("/information")
	public String information(@RequestParam(value="p",required=false) String p,
			HttpServletRequest request){
		Page page=new Page();
		if(p==null || p.equals("")){
			page.setPage(1);
		}else{
			page.setPage(Integer.parseInt(p));
		}
		page.setPageSize(3);
		for (Information information : informationService.getInformationByPage((page.getPage()-1)*page.getPageSize(), page.getPageSize(),null)) {
			page.getItems().add(information);
		}
		page.setPageCount(informationService.getAllInformation().size()%page.getPageSize()==0?informationService.getAllInformation().size()/page.getPageSize():informationService.getAllInformation().size()/page.getPageSize()+1);
		request.setAttribute("page", page);
		request.setAttribute("dicList",dataDictionaryService.getDataDictionaryByType("INFO_TYPE"));
		return "informanage/information";
	}
	
	@RequestMapping("/viewInfo")
	@ResponseBody
	public Object viewInfo(String id){
		Information information=informationService.getInformationById(id);
		if(information==null){
			return "nodata";
		}
		return information;
	}
	
	@RequestMapping("/addInformation")
	public String addInformation(Information information,HttpServletRequest request){
		SysUser sysUser=(SysUser) request.getSession().getAttribute("user");
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		information.setState(1);
		information.setPublisher(sysUser.getLoginCode());
		information.setPublishTime(new Date());
		if(information.getFilePath()!=null){
			File file=new File(path+File.separator+information.getFilePath().replace("\"", ""));
			information.setFilePath(path+File.separator+information.getFilePath().replace("\"", ""));
			information.setUploadTime(new Date());
			information.setFileName(file.getName());
			information.setFileSize(Double.parseDouble(file.length()+""));
		}
		if(informationService.addInformation(information)>0){
			System.out.println("添加成功！");
		}
		return "redirect:information";
	}
	
	@RequestMapping("/delInfo")
	@ResponseBody
	public Object delInfo(String id){
		Information information=informationService.getInformationById(id);
		if(information.getFilePath()!=null){
			File file=new File(information.getFilePath());
			file.delete();
		}
		if(information==null){
			return "nodata";
		}else{
			if(information.getFilePath()!=null){
				File file=new File(information.getFilePath());
				file.delete();
			}
			if(informationService.delInformationById(information.getId().toString())>0){
				return "success";
			}
			return "failed";
		}
	}
	
	@RequestMapping("/modifyInfoState")
	@ResponseBody
	public Object modifyInfoState(String inforState){
		Information information=JSONArray.parseObject(inforState, Information.class);
		if(informationService.modifyInfoState(information.getId().toString(), information.getState().toString())>0){
			return "success";
		}	
		return "failed";
	}

	@RequestMapping(value="/upload")
	@ResponseBody
	public Object upload(@RequestParam(value = "uploadInformationFile", required = false) MultipartFile uploadInformationFile,
							HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		System.out.println(path);
		String oldFileName ="";
		int filesize = 5000000;
		if (uploadInformationFile != null) {
			oldFileName = uploadInformationFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (uploadInformationFile.getSize() > filesize) {// 上传大小不得超过 50k
				return 1;
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("txt") || prefix.equalsIgnoreCase("mp4")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_information."+prefix;
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					uploadInformationFile.transferTo(targetFile);
					return fileName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 2;
	}
	
	@RequestMapping("/delInfoFile")
	@ResponseBody
	public Object delInfoFile(String filePath,HttpServletRequest request){
		File filePaths=new File(filePath);
		if(filePaths.delete()){
			return "success";
		}
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles")+File.separator+filePath.replace("\"", "");
		File file=new File(path);
		if(file.delete()){
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping("/modifyinformation")
	public String modifyInformation(Information information,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		if(information.getFilePath()!=null && !information.getFilePath().equals("")){
			File file=new File(path+File.separator+information.getFilePath().replace("\"", ""));
			information.setFilePath(path+File.separator+information.getFilePath().replace("\"", ""));
			information.setUploadTime(new Date());
			information.setFileName(file.getName());
			information.setFileSize(Double.parseDouble(file.length()+""));
		}else{
			information.setFilePath(null);
			information.setUploadTime(null);
			information.setFileName(null);
			information.setFileSize(null);
		}
		informationService.modifyInformation(information);
		return "redirect:information";
	}
	
//---Information---stop
	
	//首页点击
	@RequestMapping("/portalAfficheDetail")
	public String portalAfficheDetail(String id, HttpServletRequest request) {
		request.setAttribute("affiche", afficheService.getAfficheById(id));
		return "informanage/portalaffichedetail";
	}
	//首页点击
	@RequestMapping("/portalInfoDetail")
	public String portalInfoDetail(String id, HttpServletRequest request) {
		request.setAttribute("information", informationService.getInformationById(id));
		return "informanage/portalinfodetail";
	}
	//首页列表
	@RequestMapping("/portalafficheList")
	public String portalafficheList(@RequestParam(value = "p", required = false) String p, HttpServletRequest request) {
		Page page = new Page();
		page.setPageSize(1);
		if (p == null || p.equals("")) {
			p = "1";
			page.setPage(1);
		} else {
			page.setPage(Integer.parseInt(p));
		}
		for (Affiche affiche : afficheService.getAfficheByPage(Integer.parseInt(p), page.getPageSize())) {
			page.getItems().add(affiche);
		}
		page.setPageCount(afficheService.getAllAffiche().size() / page.getPageSize());
		request.setAttribute("page", page);
		return "informanage/portalaffichelist";
	}
	//首页列表
	@RequestMapping("/portalinfoList")
	public String portalinfoList(@RequestParam(value = "p", required = false) String p, HttpServletRequest request) {
		Page page = new Page();
		page.setPageSize(1);
		if (p == null || p.equals("")) {
			p = "1";
			page.setPage(1);
		} else {
			page.setPage(Integer.parseInt(p));
		}
		for (Information information : informationService.getInformationByPage(Integer.parseInt(p),
				page.getPageSize(),null)) {
			page.getItems().add(information);
		}
		page.setPageCount(informationService.getAllInformation().size() / page.getPageSize());
		request.setAttribute("page", page);
		return "informanage/portalinfolist";
	}

	@RequestMapping("/download")
	public String download(String path) throws Exception {
		String dPath = "D:\file" + path;
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
		// 转码，免得文件名中文乱码
		dPath = URLEncoder.encode(dPath, "UTF-8");
		return "";
	}
	
	@RequestMapping("/downloadcenter")
	public String  downloadcenter(@RequestParam(value="p",required=false) String p,
			HttpServletRequest request){
		Page page=new Page();
		if(p==null || p.equals("")){
			page.setPage(1);
		}else{
			page.setPage(Integer.parseInt(p));
		}
		page.setPageSize(3);
		for (Information information : informationService.getInformationByPage((page.getPage()-1)*page.getPageSize(), page.getPageSize(),"downOk")) {
			page.getItems().add(information);
		}
		page.setPageCount(informationService.getAllInformation().size()%page.getPageSize()==0?informationService.getAllInformation().size()/page.getPageSize():informationService.getAllInformation().size()/page.getPageSize()+1);
		request.setAttribute("page", page);
		request.setAttribute("dicList",dataDictionaryService.getDataDictionaryByType("INFO_TYPE"));
		return "informanage/downloadcenter";
	}

}
