var pos=function(x,y)
		{
			this.x=x;
			this.y=y;		
		};
var initPoint;
var increment=new pos(0,0);
var tempAxis;
var dragging=function()
	{
	this.target;
	this.mouseDown;//用来判断鼠标是否按下，初始状态为false，表示未按下
	this.up=function(){this.mouseDown=false;
		}
	this.init=function(targetDiv)
			  {
				  
				this.target=document.getElementById(targetDiv);
				this.mouseDown=false;
			  }
	this.move=function(e)//处理鼠标移动
		 	  {
				if(this.mouseDown)//确保鼠标移动的同时已经按下
			   	{
					increment.x=e.screenX-initPoint.x;
					increment.y=e.screenY-initPoint.y;
					if(increment.x!=0||increment.y!=0)
					{
					this.target.style.left=tempAxis.x+increment.x+"px";
					this.target.style.top=tempAxis.y+increment.y+"px";
					}
				}		
			 }
	
	this.down=function(e)
			  {
				  this.mouseDown=true;//鼠标按下
				  initPoint=new pos(e.screenX,e.screenY);//鼠标按下那一刻鼠标的位置
				  tempAxis=new pos(parseInt(this.target.style.left),parseInt(this.target.style.top));//用来存储移动目标的初始坐标
			  }
	};
	var drag=new dragging();
	drag.init("target");
