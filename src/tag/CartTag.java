package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import bookOnline.Cart;
public class CartTag extends SimpleTagSupport
{
	private Cart cart;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(cart!=null)
		{
			out.print("<div class='cart'>");
	        out.print("<div class='title'><span class='title_icon'><img src='images/cart.gif' alt='' title='' /></span>My cart</div>");
	        out.print("<div class='home_cart_content'>"+cart.getTotalNumber()+"x books | <span class='red'>TOTAL: "+cart.getTotalPrice()+"Ԫ</span> </div>");
	        out.print("<a href='cart.jsp' class='view_cart' target='_blank'>view cart</a> </div>");
		}
	}
	public void setCart(Cart cart)
	{
		this.cart=cart;
	}
}
