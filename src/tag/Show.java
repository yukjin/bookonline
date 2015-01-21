package tag;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bookOnline.BookInfo;

public class Show extends SimpleTagSupport
{
	/**显示书本的查询结果标签*/
	private BookInfo[] bookList;
	private String showType;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();
		if(showType.equals("category"))		
		{
			showBookListByCategory(out);
		}
		else if(showType.equals("new"))
		{
			showBookListByNew(out);
		}
		else if(showType.equals("hot"))
		{
			showBookListByHot(out);
		}
	}
	public void setBookList(BookInfo[] bookList)
	{
		this.bookList=bookList;
	}
	public void setShowType(String showType)
	{
	this.showType=showType;	
	}
	private void showBookListByHot(JspWriter out) throws IOException
	{
		if(bookList==null)
		{
			out.print("无相关书籍");
		}
		else
		{
			for(int i=0;i<bookList.length;i++)
			{
				out.print("<div class='feat_prod_box'>");
				out.print("<div class='prod_img'><a href='details.do?bookname="+bookList[i].getBookname()+"' target='_blank'><img src='images/pre"+bookList[i].getBookpic()+"' alt='' title='' border='0' /></a></div>");
				out.print("<div class='prod_det_box'>");
				out.print("<div class='box_top'></div>");
				out.print("<div class='box_center'>");
				out.print("<div class='prod_title'>"+bookList[i].getBookname()+"</div>");
	            out.print("<p class='details'>"+bookList[i].getBookintro()+"</p>");
	            out.print("<a href='details.do?bookname="+bookList[i].getBookname()+"' target='_blank' class='more'>- more details -</a>");
	            out.print("<div class='clear'></div>");
	            out.print("</div>");
	            out.print("<div class='box_bottom'></div>");
	            out.print("</div>");
	            out.print("<div class='clear'></div>");
	            out.print("</div>");
			}		
		}
	}
	private void showBookListByNew(JspWriter out) throws IOException
	{
		if(bookList==null)
		{
			out.print("无相关书籍");
		}
		else
		{
			for(int i=0;i<bookList.length;i++)
			{
				out.print("<div class='feat_prod_box'>");
				out.print("<div class='prod_img'><a href='details.do?bookname="+bookList[i].getBookname()+"'><img src='images/pre"+bookList[i].getBookpic()+"' alt='' title='' border='0' /></a></div>");
				out.print("<div class='prod_det_box'> <span class='special_icon'><img src='images/new_icon.gif' alt='' title='' /></span>");
				out.print("<div class='box_top'></div>");
				out.print("<div class='box_center'>");
				out.print("<div class='prod_title'>"+bookList[i].getBookname()+"</div>");
	            out.print("<p class='details'>"+bookList[i].getBookintro()+"</p>");
	            out.print("<a href='details.do?bookname="+bookList[i].getBookname()+"' class='more'>- more details -</a>");
	            out.print("<div class='clear'></div>");
	            out.print("</div>");
	            out.print("<div class='box_bottom'></div>");
	            out.print("</div>");
	            out.print("<div class='clear'></div>");
	            out.print("</div>");
			}		
		}
	}
	private void showBookListByCategory(JspWriter out) throws IOException
	{
		if(bookList==null)
		{
			out.print("无相关书籍");
		}
		else
		{
			for(int i=0;i<bookList.length;i++)
			{
  			out.print("<div class='new_prod_box'>");
  			out.print("<a href='details.do?bookname="+bookList[i].getBookname()+"'>"+bookList[i].getBookname()+"</a>");
  			out.print("<div class='new_prod_bg'>");
  			out.print("<a href='details.do?bookname="+bookList[i].getBookname()+"'>"+"<img src='images/"+"small"+bookList[i].getBookpic()+"' alt='' title='' class='thumb' border='0' /></a> </div>");
    		out.println("</div>");
			}
  		}
	}
}
