jQuery.noConflict();
  jQuery(function(){
	jQuery("table tr:nth-child(odd)").css("background-color","#eee");//隔行变色
	jQuery("#chkAll").click(function(){
		if(this.checked)//if itself has been checked
		{
			jQuery("table tr td input[type=checkbox]").attr("checked",true);
		}
		else
		{
			jQuery("table tr td input[type=checkbox]").attr("checked",false);
		}
		})
	/*delete btn event*/
	jQuery("#btnDel").click(function(){
		var intL=jQuery("table tr td input:checked:not('#chkAll')").length;//get the options that were checked except chkAll
		if(intL!=0)
		{
			jQuery("table tr td input[type=checkbox]:not('#chkAll')").each(function(index)
			{
				if(this.checked)
				{
				jQuery("table tr[id="+this.value+"]").remove(); 
				}
			})	
		}
		})
	/*小图片鼠标移动事件*/
	var x=5,y=15;
	jQuery("table tr td img").mousemove(function(e){
		jQuery("#imgTip").attr("src",this.src).css({"top":(e.pageY+y)+"px","left":(e.pageX+x)+"px"}).show(1000);
		})
	jQuery("table tr td img").mouseout(function(){jQuery("#imgTip").css("display","none");})
	 })