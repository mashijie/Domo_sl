<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!--
		Charisma v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="utf-8">
	<title>SL会员商城</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">

	<!-- The styles -->
	<link href="${pageContext.request.contextPath}/statics/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	  .navbar .nav li a{
	    border:0px;
	}
	.custom-setting{}
	.clear{clear: both;}
	div .modal-body label {
		color:black;
	}
	
	#menus a{
		text-decoration: none;
	}
	#menus li{
	cursor:pointer;
	}

	#menus li div{
		display:none;
	}
	</style>
	<link href="${pageContext.request.contextPath}/statics/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/charisma-app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='${pageContext.request.contextPath}/statics/css/fullcalendar.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='${pageContext.request.contextPath}/statics/css/chosen.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/uniform.default.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/colorbox.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/jquery.noty.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/noty_theme_default.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/elfinder.min.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/elfinder.theme.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/opa-icons.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/css/uploadify.css' rel='stylesheet'>
	<!-- 华丽丽滴分割线 北大青鸟 start 2014-->
	<!-- add by bdqn_hl 2014-2-28 start-->
	<link href='${pageContext.request.contextPath}/statics/localcss/userlist.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/rolelist.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/authoritymanage.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/dicmanage.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/affiche.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/information.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/addgoodspack.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/mymessage.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/statics/localcss/messagelist.css' rel='stylesheet'>
	<!-- add by bdqn_hl 2014-2-28 end-->
	
	<!-- 华丽丽滴分割线 北大青鸟 end 2014 -->
	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/img/favicon.ico">
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#menus li").click(function(){
				$(this).nextAll().children().hide();
				$(this).prevAll().children().hide();
				$(this).children().show();	
			});
		});
	</script>
	
	
	
</head>
<body>
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="${pageContext.request.contextPath}/main.html"> <img alt="Charisma Logo" src="${pageContext.request.contextPath}/statics/img/logo20.png" /> <span>SL会员商城</span></a>
				<div class="btn-group pull-right" >
					<ul class="nav">
						<li><a href="#">你好，${user.loginCode}</a></li>
						<li><a href="#">角色：${user.roleName}</a></li>
						<li><a href="${pageContext.request.contextPath}/main">首页</a></li>
						<li><a href="#">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/message/mymessage.html">留言板</a></li>
						<li><a href="${pageContext.request.contextPath}/SysUser/updatePwd" class="btn-setting modifypwd">修改密码</a></li>
						<li><a href="${pageContext.request.contextPath}/logout">注销</a></li>
					</ul>
				</div>
				<div class="modal hide fade" id="myModal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>修改密码</h3>
					</div>
					<div class="modal-body">
						<p>
								<label>请输入原密码：</label>
								  <input id="oldpassword"  type="password">
								  <span style="color:red;font-weight: bold;">*</span>
								<label>请输入新密码：</label>
								  <input id="newpassword"  type="password">
								  <span style="color:red;font-weight: bold;">*新密码必须6位以上</span>
								<label>再次输入新密码：</label>
								  <input id="aginpassword"  type="password">
								  <span style="color:red;font-weight: bold;">*</span>
						</p>
						<p id="modifypwdtip">
						</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn" data-dismiss="modal">取消</a>
						<a href="#" id="modifySavePassword" class="btn btn-primary">修改</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu" id="menus">
<!-- 						<li> -->
<!-- 							后台管理 -->
<!-- 							<div> -->
<%-- 								<a href="${pageContext.request.contextPath}/SysUser/addUser">&nbsp;&nbsp;新建用户</a><br> --%>
<!-- 								<a>&nbsp;&nbsp;用户管理</a><br> -->
<!-- 							</div> -->
<!-- 						</li> -->
						
<!-- 						<li> -->
<!-- 							会员管理 -->
<!-- 							<div> -->
<!-- 								<a>&nbsp;&nbsp;会员管理</a><br> -->
<%-- 								<a href="${pageContext.request.contextPath}/SysUser/updatePwd">&nbsp;&nbsp;修改密码</a><br> --%>
<!-- 							</div> -->
<!-- 						</li> -->
						
						 <c:if test="${functionList != null}">
						  <c:forEach items="${functionList}" var="function">
								<c:if test="${function.parentId == 0 }">
									<li>${function.functionName }<div>
										  <c:forEach items="${functionList}" var="sonFunction">
										  	<c:if test="${sonFunction.parentId == function.id }">
										  		<a href="${sonFunction.funcUrl }">${sonFunction.functionName }</a><br>
										  	</c:if>
										  </c:forEach>
										  </div> 
									</li>
								</c:if>
						  </c:forEach>
						 </c:if> 
					
					</ul>
					<!-- 
					<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
				 	-->
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
