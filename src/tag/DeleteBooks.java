package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bookOnline.BookInfo;
import bookOnline.Cart;

public class DeleteBooks extends SimpleTagSupport
{
	private String[] booksIndex;
	private Cart cart;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(booksIndex!=null)
		{
			
			BookInfo[] bookList=cart.toBookInfoArray();
			for(int i=0;i<booksIndex.length;i++)
			{
				cart.removeBook(bookList[Integer.parseInt(booksIndex[i])]);
			}
		}
	
	}
	public void setBooksIndex(String[] booksIndex)
	{
		this.booksIndex=booksIndex;
	}
	public void setCart(Cart cart)
	{
		this.cart=cart;
	}
}

