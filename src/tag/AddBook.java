package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bookOnline.BookInfo;
import bookOnline.Cart;

public class AddBook extends SimpleTagSupport
{
	private String add;
	private Cart cart;
	private BookInfo bookinfo;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(add!=""&&cart!=null&&bookinfo!=null)
		{
			cart.updateBook(bookinfo,1);
		}
	}
	public void setAdd(String add)
	{
		this.add=add;
	}
	public void setCart(Cart cart)
	{
		this.cart=cart;
	}
	public void setBookinfo(BookInfo bookinfo)
	{
		this.bookinfo=bookinfo;
	}
}
