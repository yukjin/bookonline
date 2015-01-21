package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bookOnline.BookInfo;
import bookOnline.Cart;

public class AddBooks extends SimpleTagSupport
{
	private String[] quantity;
	private Cart cart;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(quantity!=null)
		{
			BookInfo[] bookList=cart.toBookInfoArray();
			for(int i=0;i<bookList.length;i++)
			{
				cart.updateBook(bookList[i],Integer.parseInt(quantity[i]));
			}
		}
	
	}
	public void setQuantity(String[] quantity)
	{
		this.quantity=quantity;
	}
	public void setCart(Cart cart)
	{
		this.cart=cart;
	}
}

