//<![CDATA[
$(document).ready(function(){

	new jPlayerPlaylist({
		jPlayer: "#jquery_jplayer_1",
		cssSelectorAncestor: "#jp_container_1"
	}, [
		{
			title:"Because of You",
			mp3:"musiclist/Because of You.mp3",
			oga:""
		},
		{
			title:"Britney Spears - Baby One More Time",
			mp3:"musiclist/Britney Spears - Baby One More Time.mp3",
			oga:""
		},
		{
			title:"Britney Spears - Hold It Against Me",
			mp3:"musiclist/Britney Spears - Hold It Against Me.mp3",
			oga:""
		},
		{
			title:"TBT - The Emergency",
			mp3:"musiclist/BT - The Emergency.mp3",
			oga:""
		},
		{
			title:"Groove Coverage - she",
			mp3:"musiclist/Groove Coverage - she.mp3",
			oga:""
		},
		{
			title:"Justin Bieber - Favorite Girl",
			free:true,
			mp3:"musiclist/Justin Bieber - Favorite Girl.mp3",
			oga:""
		},
		{
			title:"Justin Bieber - Never Say Never",
			mp3:"musiclist/Justin Bieber - Never Say Never.mp3",
			oga:""
		},
		{
			title:"KARA - Rock U",
			mp3:"musiclist/KARA - Rock U.mp3",
			oga:""
		},
		{
			title:"Beside Me",
			mp3:"http://www.jplayer.org/audio/mp3/Miaow-06-Beside-me.mp3",
			oga:"http://www.jplayer.org/audio/ogg/Miaow-06-Beside-me.ogg"
		},
		{
			title:"Bubble",
			free:true,
			mp3:"http://www.jplayer.org/audio/mp3/Miaow-07-Bubble.mp3",
			oga:"http://www.jplayer.org/audio/ogg/Miaow-07-Bubble.ogg"
		},
		{
			title:"Stirring of a Fool",
			mp3:"http://www.jplayer.org/audio/mp3/Miaow-08-Stirring-of-a-fool.mp3",
			oga:"http://www.jplayer.org/audio/ogg/Miaow-08-Stirring-of-a-fool.ogg"
		},
		{
			title:"Lady GaGa - Bad Romance",
			free: true,
			mp3:"musiclist/Lady GaGa - Bad Romance.mp3",
			oga:""
		},
		{
			title:"Lady Antebellum - Need You Now",
			mp3:"musiclist/Lady Antebellum - Need You Now.mp3",
			oga:""
		}
	], {
		swfPath: "js",
		supplied: "oga, mp3",
		wmode: "window"
	});
});
//]]>