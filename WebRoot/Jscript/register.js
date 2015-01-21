jQuery.noConflict();
jQuery(function(){
	var pram={"txtUserName":"","txtPswd":"","txtEmail":"","txtPhone":"","txtProfession":"","txtAddr":""};
	var error=false;
	jQuery("#txtUserName").trigger("focus").css("border-color","#0cc");//用户名获得焦点
	jQuery("#txtUserName").bind({
		blur:function(){
			 var txt=jQuery(this).val();
				 if(txt.length==0)
				 {
					jQuery("#txtUserName").css("border-color","#ff0000");
					jQuery("#userNameTip").show().html("<font color='ff0000'>昵称不能为空!</font>").addClass("fill_error").show();
					error=true;		 
				 }
				 else if(txt.length>10)
				 {
					jQuery("#txtUserName").css("border-color","#ff0000");
					jQuery("#userNameTip").html("<font color='ff0000'>昵称超过10字!</font>").addClass("fill_error").show();
					error=true;
				 }
				 else
				 {
					 jQuery.ajax({
		       			url:"register.do",
						type:"post",
						data:{txtUserName:jQuery("#txtUserName").val()},
						success:function(returnValue)
						{
							if(returnValue=="用户名已存在")
							{
								error=true;
							}
							else
							{
								error=false;
							}
							jQuery("#txtUserName").css("border-color","#999");
							jQuery("#userNameTip").html(returnValue).removeClass("fill_error").show();
							
						}
		
						})
				 }//ajax判断用户名是否被占用
			
			}
		})//用户名
		
				
	jQuery("#txtPswd").bind({
		blur:function(){
			 var txt=jQuery(this).val();
				 if(txt.length==0)
				 {
					jQuery("#txtPswd").css("border-color","#ff0000");
					jQuery("#pswdTip").show().html("<font color='ff0000'>密码不能为空!</font>").addClass("fill_error").show();		 
					error=true;
				 }
				 else if(txt.length>10)
				 {
					jQuery("#txtPswd").css("border-color","#ff0000");
					jQuery("#pswdTip").show().html("<font color='ff0000'>密码不能超过16字!</font>").addClass("fill_error").show();
					error=true;
				 }
				 else
				 {
					 jQuery("#pswdTip").html("");
					 jQuery("#pswdTip").append("<img src='images/pic_Email_ok.gif'>").removeClass("fill_error").show();
					 jQuery("#txtPswd").css("border-color","#999");
					 error=false;
				 }
			
			
			  }
		 })//密码
		 
		
		jQuery("#txtEmail").bind({
			blur:function(){
				 var vtxt = jQuery("#txtEmail").val();
              	 if (vtxt.length == 0) 
				 {
               
					jQuery("#txtEmail").css("border-color","#ff0000");
					jQuery("#emailTip").show().html("<font color='ff0000'>邮箱不能为空!</font>").addClass("fill_error").show();
					error=true;
               	 }
				 else if(chkEmail(vtxt))
				 {
					 jQuery("#emailTip").html("");
					 jQuery("#emailTip").append("<img src='images/pic_Email_ok.gif'>").removeClass("fill_error").show();
					 jQuery("#txtEmail").css("border-color","#999");
					 error=false;
					 
				 }
				 else
				 {
					jQuery("#emailTip").show().html("<font color='ff0000'>格式有误!</font>").addClass("fill_error").show();
					jQuery("#txtEmail").css("border-color","#ff0000");	
					error=true;
			     }
				   
				}
			
			})
	
            function chkEmail(strEmail) 
			{
                if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(strEmail)) 
				{
                    return false;
                }
                else 
				{
                    return true;
                }
            }//匹配email格式
	 
	jQuery(".register").click(function(){

		jQuery(".contact_input").each(function(index){
			if(jQuery(this).val()=="")
			{
				error=true;
			}
			})


		if(!error)
		{

			pram[jQuery("#txtUserName").attr("id")]=jQuery("#txtUserName").val();
			pram[jQuery("#txtPswd").attr("id")]=jQuery("#txtPswd").val();
			pram[jQuery("#txtEmail").attr("id")]=jQuery("#txtEmail").val();
			pram[jQuery("#txtPhone").attr("id")]=jQuery("#txtPhone").val();
			pram[jQuery("#txtProfession").attr("id")]=jQuery("#txtProfession").val();
			pram[jQuery("#txtAddr").attr("id")]=jQuery("#txtAddr").val();

			 jQuery.ajax({
		       			url:"register.do",
						type:"post",
						data:pram,
						success:function(returnValue)
						{
							if(returnValue=="失败")
							{
								alert(returnValue);
							}
							else
							{
							var tips="<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><center><h1><font color='#00CCCC'>账号申请成功！</font></h1></center>";
							document.getElementById("leftcontent").innerHTML=tips;
							}
						}		
				   })
		}
		
		})
	})

