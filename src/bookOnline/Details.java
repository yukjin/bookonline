package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.ConnectionPool;
import store.ConnectionPoolImpl;
import store.DBService;
import store.DBServiceImpl;

public class Details extends HttpServlet {

	DBService dbService;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String bookname=request.getParameter("bookname");
		bookname=convert(bookname);//处理中文乱码
		try 
		{
			BookInfo bookinfo=dbService.queryByBookName(bookname);//根据书名查询书本详细信息
			BookInfo[] bookList=dbService.getRelatedBook(bookinfo.getBookname(),bookinfo.getBooktype());
			request.setAttribute("bookinfo",bookinfo);
			request.setAttribute("bookList",bookList);
			String path="/details.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//请求转发至details.jsp
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			response.sendRedirect("index.jsp");
			e.printStackTrace();
		}
	
		
	}
	public String convert(String booktype) throws UnsupportedEncodingException
	{
		return new String(booktype.getBytes("ISO-8859-1"),"utf-8");
	}
	public void init() throws ServletException 
	{
		try {
			dbService=new DBServiceImpl();
		} catch (Exception e)
		{
			e.printStackTrace();
		}//数据库服务代理
	}

}
