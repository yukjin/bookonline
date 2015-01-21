jQuery.noConflict();
jQuery(function()
{
	var txtBookRank;
	jQuery(".rank").click(function(){
		jQuery.ajax({
		       			url:"score.do",
						type:"get",
						data:{bookRank:txtBookRank,bookId:jQuery("#isbn").text()},
						success:function(returnValue)
						{
							alert(returnValue);
						}
		
			 })
		
	})
jQuery(".rank").bind({
		mouseover:function(){
			/*for(var i=1;i<=jQuery(this).val();i++)
			{
			jQuery("img[value='"+i+"']").attr("src","images/aft.png");
			}*/
		
			jQuery("img.rank").each(function(index,element){
					
					jQuery("img[value='"+(index+1)+"']").attr("src","images/aft.png");
					if(event.target==element)//获取触发对象
					{
						txtBookRank=index+1;
						return false;
					}
					
				})
		},
		mouseout:function(){
		
					jQuery("img.rank").each(function(index,element){
					jQuery("img[value='"+(index+1)+"']").attr("src","images/pre.png");
					if(event.target==element)
					{
						return false;
					}
					
					})
		}	
		})
})
