$(function(){
		$("#oldPswd").trigger("focus").css("border-color","#0cc");
		var error=false;
		$("#oldPswd").bind({
		blur:function(){
			 var txt=$(this).val();
				 if(txt.length==0)
				 {
					$("#oldPswd").css("border-color","#ff0000");
					$("#oldPswdTip").show().html("<font color='ff0000'>旧密码不能为空!</font>").addClass("fill_error").show();	 
					error=true;
				 }
				 else
				 {
					 $.ajax({
		       			url:"pswdUpDate.php",
						type:"post",
						data:{oldPswd:$("#oldPswd").val()},
						success:function(returnValue)
						{
							if(returnValue=="密码输入正确")
							{
							$("#oldPswd").css("border-color","#999");
							$("#oldPswdTip").html("<font color='#ffffff'>"+returnValue+"</font>").removeClass("fill_error").show();
							error=false;
							}
							else
							{
								$("#oldPswdTip").html("<font color='ff0000'>密码输入有误</font>").removeClass("fill_error").show();
								error=true;
							}
						}
			
						})
				 }
			
			}
		})
		
		
		$("#txtPswd").bind({
		blur:function(){
			 var txt=$(this).val();
				 if(txt.length==0)
				 {
					$("#txtPswd").css("border-color","#ff0000");
					$("#pswdTip").show().html("<font color='ff0000'>密码不能为空!</font>").addClass("fill_error").show();		 
					error=true;
				 }
				 else if(txt.length>16)
				 {
					$("#txtPswd").css("border-color","#ff0000");
					$("#pswdTip").show().html("<font color='ff0000'>密码不能超过16字!</font>").addClass("fill_error").show();
					error=true;
				 }
				 else
				 {
					 $("#pswdTip").html("");
					 $("#pswdTip").append("<img src='Images/pic_Email_ok.gif'>").removeClass("fill_error").show();
					 $("#txtPswd").css("border-color","#999");
					 error=false;
				 }
			
			
			  }
		 })//密码
		 
		
		$("#txtconfirmPswd").bind({
			blur:function(){
				if($(this).val().length==0)
				 {
					 $("#txtconfirmPswd").css("border-color","#ff0000");
					 $("#confirmTip").show().html("<font color='ff0000'>您还没有确认密码!</font>").addClass("fill_error").show();
					 error=true;
				}
				else if($("#txtPswd").val()==$(this).val())
				 {
					 $("#confirmTip").html("");
					 $("#confirmTip").append("<img src='Images/pic_Email_ok.gif'>").removeClass("fill_error").show();
					 $("#txtconfirmPswd").css("border-color","#999");
					 error=false;
				 }
				else
				{
					$("#txtconfirmPswd").css("border-color","#ff0000");
					$("#confirmTip").show().html("<font color='ff0000'>密码不一致!</font>").addClass("fill_error").show();	
					error=true;
				}	
			 
			}
		}) 
	$("#update").click(function()
	{
		$("input.pass_reg_input").each(function(index){
			if($(this).val()=="")
			{
				error=true;
			}
			})
		if(!error)
		{
			 $.ajax({
		       			url:"pswdUpDate.php",
						type:"post",
						data:{txtPswd: $("#txtPswd").val()},
						success:function(returnValue)
						{
							$("#func_content").html("<br/><br/><br/><br/><center><h1>密码修改成功！</h1><br/><br/><br><br><br/><br/><br/><br>");
						}		
				   })
		}
	})
	
})