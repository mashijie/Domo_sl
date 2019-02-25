package org.slsale.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.SysUser;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.MemberService;
import org.slsale.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import pers.sj.util.Page;

@Controller
@RequestMapping("/Member")
public class MemberController {

	@Resource
	DataDictionaryService dataDictionaryService;
	@Resource
	SysUserService sysUserService;
	@Resource
	MemberService memberService;

	@RequestMapping("/registmember")
	public String registMember(HttpServletRequest request) {
		request.setAttribute("cardTypeList", dataDictionaryService.getDataDictionaryByType("CARD_TYPE"));
		return "member/registmember";
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(@RequestParam(value = "a_fileInputID", required = false) MultipartFile cardFile,
			@RequestParam(value = "a_fileInputBank", required = false) MultipartFile bankFile,
			@RequestParam(value = "m_fileInputID", required = false) MultipartFile m_fileInputID,
			@RequestParam(value = "m_fileInputBank", required = false) MultipartFile m_fileInputBank,
			@RequestParam(value = "loginCode", required = false) String loginCode, HttpServletRequest request) {
		// 定义项目下创建的uploadfiles文件路径
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		// 创建字典，获取到个人上传文件的大小
		List<DataDictionary> list = null;
		list = dataDictionaryService.getDataDictionaryByType("PERSONALFILE_SIZE");
		int filesize = 50000;
		if (cardFile != null) {
			String oldFileName = cardFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (cardFile.getSize() > filesize) {// 上传大小不得超过 50k
				return 1;
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_IDcard.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					cardFile.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return request.getContextPath() + "/statics/uploadfiles/" + fileName;
			} else {
				return 2;
			}
		}
		if (bankFile != null) {
			String oldFileName = bankFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (bankFile.getSize() > filesize) {// 上传大小不得超过 50k
				return 1;
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_IDcard.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					bankFile.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return request.getContextPath() + "/statics/uploadfiles/" + fileName;
			} else {
				return 2;
			}
		}
		if (m_fileInputID != null) {
			String oldFileName = m_fileInputID.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (m_fileInputID.getSize() > filesize) {// 上传大小不得超过 50k
				return 1;
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_IDcard.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					m_fileInputID.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return request.getContextPath() + "/statics/uploadfiles/" + fileName;
			} else {
				return 2;
			}
		}
		if (m_fileInputBank != null) {
			String oldFileName = m_fileInputBank.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (m_fileInputBank.getSize() > filesize) {// 上传大小不得超过 50k
				return 1;
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_IDcard.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					m_fileInputBank.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return request.getContextPath() + "/statics/uploadfiles/" + fileName;
			} else {
				return 2;
			}
		}
		return "1";
	}

	@RequestMapping("/delpic")
	@ResponseBody
	public Object delpic(String picpath, String id, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath(picpath.substring(9, picpath.length()));
		System.out.println(path);
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			return "success";
		}
		return "faild";
	}
	//注册
	@RequestMapping("/saveregistmember")
	public String saveregistmember(SysUser sysUser,HttpServletRequest request) {
		sysUser.setCreateTime(new Date());
		sysUser.setRoleId(2);
		sysUser.setRoleName("会员");
		sysUser.setUserType("1");
		sysUser.setUserTypeName("注册会员");
		if (sysUserService.addUser(sysUser) > 0) {
			request.setAttribute("registUser", sysUser);
		}
		return "member/registok";
	}

	//条款
	@RequestMapping("/registrule")
	public String registRule(){
		return "member/registrule";
	}
	
	
	// 会员管理~查询的都是会员
	@RequestMapping("/memberlist")
	public String memberList(@RequestParam(value = "s_loginCode", required = false) String s_loginCode,
			@RequestParam(value = "s_userName", required = false) String s_userName,
			@RequestParam(value = "p", required = false) String p, HttpServletRequest request) {
		Page page = new Page();
		page.setPageSize(1);
		if (p == null || p.equals("")) {
			page.setPage(1);
		} else {
			page.setPage(Integer.parseInt(p));
		}
		List<SysUser> memberList = memberService.getMemberList((page.getPage() - 1) * page.getPageSize(),
				page.getPageSize(), s_loginCode, s_userName);
		for (SysUser sysUser : memberList) {
			page.getItems().add(sysUser);
		}
		page.setPageCount(memberService.getMemberList(null, null, s_loginCode, s_userName).size() / page.getPageSize());
		request.setAttribute("page", page);
		request.setAttribute("s_loginCode", s_loginCode);
		request.setAttribute("s_userName", s_userName);
		request.setAttribute("cardTypeList", dataDictionaryService.getDataDictionaryByType("CARD_TYPE"));
		request.setAttribute("userTypeList", dataDictionaryService.getDataDictionaryByType("USER_TYPE"));
		return "member/memberlist";
	}

	@RequestMapping("/getuser")
	@ResponseBody
	public Object getUser(String id) {
		if (sysUserService.getUserById(id) != null) {
			return sysUserService.getUserById(id);
		} else {
			return "nodata";
		}
	}

	@RequestMapping("/logincodeisexit")
	@ResponseBody
	public String loginCodeIsExit(String loginCode, String id) {
		if (memberService.loginCodeIsExit(loginCode, id) > 0) {
			return "repeat";
		}
		return "only";
	}

	@RequestMapping("/deluser")
	@ResponseBody
	public String delUser(String delId, String delIdCardPicPath, String delBankPicPath, HttpServletRequest request) {
		if (delIdCardPicPath != null && delIdCardPicPath.length() > 9) {
			String path = request.getSession().getServletContext()
					.getRealPath(delIdCardPicPath.substring(9, delIdCardPicPath.length()));
			File file = new File(path);
			file.delete();
		}
		if (delBankPicPath != null && delBankPicPath.length() > 9) {
			String path = request.getSession().getServletContext()
					.getRealPath(delBankPicPath.substring(9, delBankPicPath.length()));
			File file = new File(path);
			file.delete();
		}
		if (sysUserService.deleteUser(delId) > 0) {
			return "success";
		}
		return "faild";
	}
	
	@RequestMapping("/modifymember")
	public String modifymember(SysUser sysUser,HttpServletRequest request){
		sysUser.setLastUpdateTime(new Date());
		if(sysUserService.updateUser(sysUser)>0){
			System.out.println("修改成功！！");
		}else{
			System.out.println("修改失败！！");
		}
		return "redirect:memberlist";
	}

	//修改本人资料
	@RequestMapping("/modifypersonalinfo")
	public String modifyPersonalInfo(HttpServletRequest request){
		SysUser sysUser=(SysUser)request.getSession().getAttribute("user");
		sysUser=sysUserService.getUserById(sysUser.getId().toString());
		if(sysUser.getIdCardPicPath()!=null){
			sysUser.setIdCardPicPath(sysUser.getIdCardPicPath().replace("\"",""));
		}
		if(sysUser.getBankPicPath()!=null){
			sysUser.setBankPicPath(sysUser.getBankPicPath().replace("\"",""));
		}
		request.setAttribute("currentUser", sysUser);
		request.setAttribute("cardTypeList", dataDictionaryService.getDataDictionaryByType("CARD_TYPE"));
		return "member/modifypersonalinfo";
	}
	
	@RequestMapping("/savepersonalinfo")
	public String savePersonalInfo(HttpServletRequest request,SysUser sysUser){
		List<DataDictionary> lists=dataDictionaryService.getDataDictionaryByType("CARD_TYPE");
		for (DataDictionary dataDictionary : lists) {
			if(sysUser.getCardType().equals(dataDictionary.getValueId().toString())){
				sysUser.setCardTypeName(dataDictionary.getValueName());
			}
		}
		sysUser.setLastUpdateTime(new Date());
		sysUserService.updateUser(sysUser);
		return "redirect:memberlist";
	}
	
}
