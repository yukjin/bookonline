package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport
{
	/**带属性的分页标签*/
	private int currPage;
	private int pageNum;
	private String category;
	private String type;
	private String searchContent;
	public void doTag()throws JspException,IOException
	{
		JspWriter out=getJspContext().getOut();	
		if(type.equals("category"))
		{
			paginationByCategory(out);	 
		}
		else if(type.equals("hot"))
		{
			paginationByHot(out);	
		}
		else if(type.equals("new"))
		{
			paginationByNew(out);
		}
		else if(type.equals("search"))
		{
			paginationBySearch(out);
		}
	}
	private void paginationBySearch(JspWriter out) throws IOException {
		
		if(currPage!=1)
        {
        	 out.print("<a href='search.do?page="+(currPage-1)+"&bookname="+searchContent+"'><<</a>");
        }
        for(int i=1;i<=pageNum;i++)
        {
        	if(i==currPage)
        	{
        		out.print("<span class='current'>"+currPage+"</span>");
        	}
        	else
        	{
        		out.print("<a href='search.do?page="+i+"&bookname="+searchContent+"'>"+i+"</a>");
        	}
        }
        if(currPage!=pageNum&&pageNum!=0)
        {
        	 out.print("<a href='search.do?page="+(currPage+1)+"&bookname="+searchContent+"'>>></a>");
        }
	}
	public void setSearchContent(String searchContent)
	{
		this.searchContent=searchContent;
	}
	public void setCurrPage(int currPage)
	{
		this.currPage=currPage;
	}
	public void setPageNum(int pageNum)
	{
		this.pageNum=pageNum;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	public void setType(String type)
	{
		this.type=type;
	}
	public void paginationByNew(JspWriter out) throws IOException
	{
		 if(currPage!=1)
         {
         	 out.print("<a href='newbook.do?page="+(currPage-1)+"'><<</a>");
         }
         for(int i=1;i<=pageNum;i++)
         {
         	if(i==currPage)
         	{
         		out.print("<span class='current'>"+currPage+"</span>");
         	}
         	else
         	{
         		out.print("<a href='newbook.do?page="+i+"'>"+i+"</a>");
         	}
         }
         if(currPage!=pageNum&&pageNum!=0)
         {
         	 out.print("<a href='newbook.do?page="+(currPage+1)+"'>>></a>");
         }
	}
	private void paginationByHot(JspWriter out) throws IOException
	{
		 if(currPage!=1)
         {
         	 out.print("<a href='hotbook.do?page="+(currPage-1)+"'><<</a>");
         }
         for(int i=1;i<=pageNum;i++)
         {
         	if(i==currPage)
         	{
         		out.print("<span class='current'>"+currPage+"</span>");
         	}
         	else
         	{
         		out.print("<a href='hotbook.do?page="+i+"'>"+i+"</a>");
         	}
         }
         if(currPage!=pageNum&&pageNum!=0)
         {
         	 out.print("<a href='hotbook.do?page="+(currPage+1)+"'>>></a>");
         }
	}
	private void paginationByCategory(JspWriter out) throws IOException
	{
		 if(currPage!=1)
         {
         	 out.print("<a href='category.do?page="+(currPage-1)+"&booktype="+category+"'><<</a>");
         }
         for(int i=1;i<=pageNum;i++)
         {
         	if(i==currPage)
         	{
         		out.print("<span class='current'>"+currPage+"</span>");
         	}
         	else
         	{
         		out.print("<a href='category.do?page="+i+"&booktype="+category+"'>"+i+"</a>");
         	}
         }
         if(currPage!=pageNum&&pageNum!=0)
         {
         	 out.print("<a href='category.do?page="+(currPage+1)+"&booktype="+category+"'>>></a>");
         }
	}
}
