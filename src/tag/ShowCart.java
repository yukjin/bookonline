package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bookOnline.BookInfo;
import bookOnline.Cart;

public class ShowCart extends SimpleTagSupport
{
	private Cart cart;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(cart!=null)
		{
			showBookListByCart(out);
		}
	}
	private void showBookListByCart(JspWriter out) throws IOException
	{
		BookInfo[]bookList=cart.toBookInfoArray();
		if(bookList==null)
		{
			out.print("购物车还是空的");
		}
		else
		{
			for(int i=0;i<bookList.length;i++)
			{
				Integer number=(Integer)cart.getBooks().get(bookList[i]);
				out.print("<tr id='0'>");
				out.print("<td>");
				out.print("<input type='checkbox' id='checkbox1' name='books' value='"+i+"'/>");
				out.print("</td>");
				out.print("<td>"+bookList[i].getBookname()+"</td>");
				out.print("<td><a href='details.do?bookname="+bookList[i].getBookname()+"'><img src='images/"+"small"+bookList[i].getBookpic()+"'/></a></td>");
				out.print("<td><input type='text' size='3' maxlength='3' name='quantity' value="+number+"><br/></td>");
				out.print("<td>"+bookList[i].getBookprice()+"元</td>");
				out.print("<td>"+bookList[i].getBookprice()*number+"元</td>");
				out.print("</tr>");
			}
  		}
	}
	public void setCart(Cart cart)
	{
		this.cart=cart;
	}
}
