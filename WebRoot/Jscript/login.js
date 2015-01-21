jQuery.noConflict();
jQuery(function() {
		jQuery("#txtLogin").click(
					function(){	
						   jQuery.ajax({
		       					url:"login.do",
								type:"post",
								data:{
									username:jQuery("#txtUsername").val(),
									password:jQuery("#txtPassword").val(),
									remember:jQuery("#txtRemember").val()
								     },
								success:function(returnValue)
								{
									if(returnValue!="success")
									{
										jQuery("#tipDiv").html(returnValue);
									}
									else
									{
										var locat=window.location.toString()
										var home=locat.substring(0,locat.length-13)+"hotbook.do";
										window.location.replace(home);
									}
								}
				   		      }) 
						  }
				     )
                }
    )
