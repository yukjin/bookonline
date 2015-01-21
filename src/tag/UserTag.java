package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import bookOnline.User;
public class UserTag extends SimpleTagSupport
{
	private User user;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(user!=null)
		{
			out.print("<div class='cart'>");
	        out.print("<div class='title'><span class='title_icon'><img src='images/bullet1.gif' alt='' title='' /></span>My order</div>");
	        out.print("<div class='home_cart_content'><a class='view_cart' href='orderState.do'>ÎÒµÄ¶©µ¥×´Ì¬</a></div></div>");
		}
	}
	public void setUser(User user)
	{
		this.user=user;
	}
}
