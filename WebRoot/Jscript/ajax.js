jQuery.noConflict();
jQuery(function() {

function showDialog(h,w)
			{
				var brsH=jQuery(window).height();
				var brsW=jQuery(window).width();
				var curH=h;
				var curW=w;
				var sclL=jQuery(window).scrollLeft();
				var sclT=jQuery(window).scrollTop();
				var left=sclL+(brsW-curW)/2;
				var top=sclT+(brsH-curH)/2;
				jQuery("#target").css({"left":left,"top":top});
			}			

jQuery("#l_music").click(function(){
	jQuery("#target").load("musicPlayer.html");
	jQuery.getScript("Jscript/music.js");
	showDialog(400,110);//调整窗体位置
	
	
	});
	jQuery("#target").click(function(){
	jQuery(".jp-playlist").toggle(1000);//单击隐藏/显示歌曲列表
	});
	jQuery("#target").dblclick(
	function(){
	jQuery("#target").html("");
	});
})