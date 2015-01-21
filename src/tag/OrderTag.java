package tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import bookOnline.Cart;
import bookOnline.Order;
public class OrderTag extends SimpleTagSupport
{
	private HashMap<String,String> orders;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(orders!=null)
		{
			Set<Entry<String,String>> orderSet=orders.entrySet();
			Iterator<Entry<String,String>> iter=orderSet.iterator();
			out.println("<table>");
			out.println("<th>");
			out.println("¶©µ¥ºÅ");
			out.println("</th>");
			out.println("<th>");
			out.println("¶©µ¥×´Ì¬");
			out.println("</th>");
			while(iter.hasNext())
			{
				Entry<String,String> entry=iter.next();
				out.println("<tr>");
				
				out.println("<td align='center'>");
				out.println(entry.getKey());
				out.println("</td>");
				
				out.println("<td align='center'>");
				out.println(entry.getValue());
				out.println("</td>");
				
				out.println("</tr>");
				
				
			}
			out.println("</table>");
		}
	}
	public void setOrders(HashMap<String,String> orders)
	{
		this.orders=orders;
	}
}