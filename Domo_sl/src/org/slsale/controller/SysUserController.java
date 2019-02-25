package org.slsale.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.SysUser;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.RoleService;
import org.slsale.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import pers.sj.util.Page;

@Controller
@RequestMapping("/SysUser")
public class SysUserController {

	@Resource
	SysUserService sysUserService;
	@Resource
	RoleService roleService;
	@Resource
	DataDictionaryService dataDictionaryService;

	// 跳转列表页面
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request, @RequestParam(value = "p", required = false) String p,
			@RequestParam(value = "s_loginCode", required = false) String s_loginCode,
			@RequestParam(value = "s_referCode", required = false) String s_referCode,
			@RequestParam(value = "s_roleId", required = false) String s_roleId,
			@RequestParam(value = "s_isStart", required = false) String s_isStart) {
		Page page = new Page();
		page.setPageSize(1);
		if (p == null || p.equals("")) {
			page.setPage(1);
		} else {
			page.setPage(Integer.parseInt(p));
		}
		List<SysUser> users = sysUserService.getAllSysUser((page.getPage() - 1) * page.getPageSize(),
				page.getPageSize(), s_loginCode, s_referCode, s_roleId, s_isStart);
		for (SysUser sysUser : users) {
			page.getItems().add(sysUser);
		}
		page.setPageCount(sysUserService.getAllSysUser(null, null, s_loginCode, s_referCode, s_roleId, s_isStart).size()
				/ page.getPageSize());
		request.setAttribute("s_loginCode", s_loginCode);
		request.setAttribute("s_referCode", s_referCode);
		request.setAttribute("s_roleId", s_roleId);
		request.setAttribute("s_isStart", s_isStart);
		request.setAttribute("roleList", roleService.getAllRole());
		request.setAttribute("page", page);
		request.setAttribute("cardTypeList", dataDictionaryService.getDataDictionaryByType("CARD_TYPE"));
		return "backend/userlist";
	}

	// 跳转修改页面
	@RequestMapping("/updatePwd")
	public String login() {
		return "member/modifypersonalpwd";
	}

	// 实现修改登录密码操作
	@RequestMapping("modifyPwd")
	@ResponseBody
	public Object modifyPwd(SysUser newSysUser, HttpServletRequest request) {
		SysUser oldsysUser = (SysUser) request.getSession().getAttribute("user");
		HashMap<String, String> result = new HashMap<String, String>();
		System.out.println(newSysUser.getPassword());
		if (newSysUser.getPassword().equals(oldsysUser.getPassword())) {
			oldsysUser.setPassword2(newSysUser.getPassword2());
			if (sysUserService.updatePwd(oldsysUser) > 0) {
				result.put("result", "success");
			} else {
				result.put("result", "failed");
			}
		} else {
			result.put("result", "oldpwdwrong");
		}
		return result;
	}

	// 实现修改二级密码操作 newSysUser.getPassword()为旧密码 newSysUser.getPassword2()为新密码
	@RequestMapping("modifySecodePwd")
	@ResponseBody
	public synchronized Object modifySecodePwd(SysUser newSysUser, HttpServletRequest request) {
		SysUser oldsysUser = (SysUser) request.getSession().getAttribute("user");
		HashMap<String, String> result = new HashMap<String, String>();
		if (newSysUser.getPassword().equals(oldsysUser.getPassword2())) {
			oldsysUser.setPassword2(newSysUser.getPassword2());
			if (sysUserService.updateSecodePwd(oldsysUser) > 0) {
				result.put("result", "success");
			} else {
				result.put("result", "failed");
			}
		} else {
			result.put("result", "oldpwdwrong");
		}
		return result;
	}

	// 添加判断用户名是否可用 ajax
	@RequestMapping("/logincodeisexit")
	@ResponseBody
	public Object logincodeisexit(String loginCode) {
		List<SysUser> users = sysUserService.getAllSysUser(null, null, null, null, null, null);
		HashMap<String, String> result = new HashMap<String, String>();
		for (SysUser sysUser : users) {
			if (loginCode.equals(sysUser.getLoginCode())) {
				result.put("result", "repeat");
				break;
			} else {
				result.put("result", "only");
			}
		}
		return result;
	}

	// 为会员添加会员类型
	@RequestMapping("/loadUserTypeList")
	@ResponseBody
	public Object loadUserTypeList(String loginCode) {
		List<DataDictionary> dataDictionarys = dataDictionaryService.getDataDictionaryByType("USER_TYPE");
		return JSONArray.toJSON(dataDictionarys);
	};

	// 实现添加用户操作
	@RequestMapping("/adduser")
	public String adduser(SysUser sysUser, HttpServletRequest request) {
	
		sysUser.setPassword("000000");
		sysUser.setPassword2("000000");
		SysUser referUser = (SysUser) request.getSession().getAttribute("user");
		sysUser.setReferId(referUser.getId());
		if (sysUserService.addUser(sysUser) > 0) {
			System.out.println("添加成功~");
			return "redirect:userList";
		}
		System.out.println("添加失败~");
		return "redirect:userList";
	}

	// 实现查看用户操作
	@RequestMapping("/getuser")
	@ResponseBody
	public Object getuser(String id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SysUser sysUser = sysUserService.getUserById(id);
		if (sysUser == null) {
			result.put("result", "nodata");
			return result;
		} else {
			return JSONArray.toJSON(sysUser);
		}
	}

	/**
	 * 实现上传图片操作
	 * 
	 * @param cardFile
	 *            ID文件
	 * @param bankFile
	 *            银行卡文件
	 * @param mCardFile
	 *            修改的ID文件
	 * @param mBankFile
	 *            修改的银行卡文件
	 * @param loginCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(@RequestParam(value = "a_fileInputID", required = false) MultipartFile cardFile,
			@RequestParam(value = "a_fileInputBank", required = false) MultipartFile bankFile,
			@RequestParam(value = "m_fileInputID", required = false) MultipartFile mCardFile,
			@RequestParam(value = "m_fileInputBank", required = false) MultipartFile mBankFile,
			@RequestParam(value = "loginCode", required = false) String loginCode, HttpServletRequest request) {
		// 定义项目下创建的uploadfiles文件路径
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		System.out.println(path);
		// 创建字典，获取到个人上传文件的大小
		List<DataDictionary> list = null;
		list = dataDictionaryService.getDataDictionaryByType("PERSONALFILE_SIZE");
		int filesize = 50000;
		if (null != list) {
			if (list.size() == 1) {
				filesize = Integer.valueOf(list.get(0).getValueName());
			}
		}
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
		if (mCardFile != null) {
			String oldFileName = mCardFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (mCardFile.getSize() > filesize) {// 上传大小不得超过 50k
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
					mCardFile.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return request.getContextPath() + "/statics/uploadfiles/" + fileName;
			} else {
				return 2;
			}
		}
		if (mBankFile != null) {
			String oldFileName = mBankFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (mBankFile.getSize() > filesize) {// 上传大小不得超过 50k
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
					mBankFile.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return request.getContextPath() + "/statics/uploadfiles/" + fileName;
			} else {
				return 2;
			}
		}
		return 2;
	}

	// 进行添加途中删除图片操作
	@RequestMapping("/delpic")
	@ResponseBody
	public Object delpic(String id, String picpath, HttpServletRequest request) {
		HashMap<String, String> result = new HashMap<String, String>();
		String path = request.getSession().getServletContext().getRealPath(picpath.substring(9, picpath.length()));
		System.out.println(path);
		File file = new File(path);
		System.out.println(file.exists());
		if (file.delete()) {
			result.put("result", "success");
		} else {
			result.put("result", "faile");
		}
		return JSONArray.toJSON(result);

	}

	// 进行修改用户操作
	@RequestMapping("/modifyuser")
	public String modifyuser(SysUser sysUser) {
		if (sysUserService.updateUser(sysUser) > 0) {
			System.out.println("修改成功~");
			return "redirect:userList";
		}
		System.out.println("修改失败~");
		return "redirect:userList";
	}

	//进行删除用户操作
	@RequestMapping("/deluser")
	@ResponseBody
	public Object deluser(String delId,String delIdCardPicPath,String delBankPicPath,String delUserType,HttpServletRequest request){
		HashMap<String, String> result=new HashMap<String,String>();
		if(delUserType!=null && delUserType.length()>0){
			result.put("result", "noallow");
			return JSONArray.toJSON(result);
		}
		if(delIdCardPicPath!=null&&delIdCardPicPath.length()>9){
			String path = request.getSession().getServletContext().getRealPath(delIdCardPicPath.substring(9, delIdCardPicPath.length()));
			File file=new File(path);
			file.delete();
		}
		if(delBankPicPath!=null&&delBankPicPath.length()>9){
			String path = request.getSession().getServletContext().getRealPath(delBankPicPath.substring(9, delBankPicPath.length()));
			File file=new File(path);
			file.delete();
		}
		if(sysUserService.deleteUser(delId)>0){
			result.put("result", "success");
			return JSONArray.toJSON(result); 
		}else{
			result.put("result", "faile");
			return JSONArray.toJSON(result); 
		}
	}
	
	
	
}
