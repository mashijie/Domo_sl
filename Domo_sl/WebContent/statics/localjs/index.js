$("#loginBtn").click(function(){	
	var user = new Object();
	user.loginCode = $.trim($("#logincode").val());
	user.password = $.trim($("#password").val());
	user.isStart = 1;
	if(user.loginCode == "" || user.loginCode == null){
		 $("#logincode").focus();
		 $("#formtip").css("color","red");
		 $("#formtip").html("对不起，登录账号不能为空。");
	}else if(user.password == "" || user.password == null){
		$("#password").focus();
		$("#formtip").css("color","red");
		$("#formtip").html("对不起，登录密码不能为空。");
	}else{
		$("#formtip").html("");
		$.ajax({
			url: 'login',
			type: 'POST',
			data:{loginCode:user.loginCode,password:user.password},
			dataType:"json",
			success: function(date){
				if(date.result != "" && "success" == date.result){
					window.location.href='main';
				}else if("failed" == date.result){
					$("#formtip").css("color","red");
					$("#formtip").html("登陆失败！请重试。");
					$("#logincode").val('');
					$("#password").val('');
				}
				else if("nologincode" == date.result){
					$("#formtip").css("color","red");
					$("#formtip").html("登录账号不存在，请重试。");
				}else if("nodata" == date.result){
					$("#formtip").css("color","red");
					$("#formtip").html("对不起，没有任何数据需要处理！请重试。");
				}else if("pwderror" == date.result){
					$("#formtip").css("color","red");
					$("#formtip").html("登录密码错误，请重试。");
				}
			},error: function(){
				$("#formtip").css("color","red");
				$("#formtip").html("登录失败！请重试。");
			}
			});
	}
});

