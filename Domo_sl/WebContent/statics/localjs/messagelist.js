$('.delmessage').click(function(e){
	//delete
	var d = $(this);
	var d_createdBy = d.attr('createdBy');
	var d_id = d.attr('id');
	if(confirm("您确定要删除用户:【"+d_createdBy+"】的该条留言吗？")){
		//delete
		$.post("../message/delmessage.html",{'delId':d_id},function(result){
			result=result.replace(/\"/g,"");
			if("success" == result){
				alert("删除成功！");
				window.location.href="../message/messagelist.html";
			}else{
				alert("删除失败！");
			}
		},'html');
	}
});

$('.replymessage').click(function(e){
	$("#reply_formtip").html('');
	var r_id = $(this).attr('id');
	$.ajax({
		url: '../message/getmessage.html',
		type: 'POST',
		data:{id:r_id},
		dataType: 'json',
		timeout: 1000,
		error: function(){
			alert("error");
		},
		success: function(result){
			if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				m =result;
				$("#message_id").val(m.id);
				$("#message_createdBy").html(m.createdBy);
				$("#message_messageContent").html(m.messageContent);
				$("#message_createTime").html(m.createTime);
				$("#replylist").html('');
//				for(var i = 0; i < m.replyList.length; i++){
//					$("#replylist").append("<li><lable><b>回复内容：</b></lable>"+m.replyList[i].replyContent+" ("+m.replyList[i].createdBy + " " + m.replyList[i].createTime +")</li>");
//				}
				
				e.preventDefault();
				$('#replyMessageDiv').modal('show');
			}
		}
	});
});

$('.viewmessage').click(function(e){
	var r_id = $(this).attr('id');
	$.ajax({
		url: '../message/getmessage.html',
		type: 'POST',
		data:{id:r_id},
		dataType: 'json',
		timeout: 1000,
		error: function(){
			alert("error");
		},
		success: function(result){
			if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				m =result
				$("#viewmessage_createdBy").html(m.createdBy);
				$("#viewmessage_messageContent").html(m.messageContent);
				$("#viewmessage_createTime").html(m.createTime);
				$("#viewreplylist").html('');
				for(var i = 0; i < m.replyList.length; i++){
					$("#viewreplylist").append("<li><lable><b>回复内容：</b></lable>"+m.replyList[i].replyContent+" ("+m.replyList[i].createdBy + " " + m.replyList[i].createTime +")</li>");
				}
				e.preventDefault();
				$('#viewreplyMessageDiv').modal('show');
			}
		}
		});
});

function replyMessageFunction(){
	$("#reply_formtip").html('');
	var result = true;
	if($.trim($("#r_content").val()) == "" || $("#r_content").val() == null){
		$("#reply_formtip").css("color","red");
		$("#reply_formtip").append("<li>对不起，回复内容不能为空。</li>");
		result = false;
	}
	return result;
}
