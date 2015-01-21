package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.DBService;
import store.DBServiceImpl;

public class Category extends HttpServlet {
	DBService dbService;//数据库服务代理
	private int pageSize;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int currPage=Integer.parseInt((String)request.getParameter("page"));
		String booktype=request.getParameter("booktype");
		Pagination pagination;//分页类
		booktype=convert(booktype);
		pagination=new Pagination(booktype,pageSize,currPage);
		try {
			
			pagination.dbService=dbService;
			BookInfo[] books=pagination.getBookInfoByType();
			int pageNum=pagination.getPageNumByCategory();//获得查询结果共有多少页
			request.setAttribute("pageNum",pageNum);//查询结果页数
			request.setAttribute("currPage",currPage);//当前页
			request.setAttribute("bookList",books);//在request中存储book查询结果
			request.setAttribute("booktype",booktype);//在request中存储booktype
			String path="/category.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//请求转发至category.jsp来显示查询结果
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String convert(String booktype) throws UnsupportedEncodingException
	{
		return new String(booktype.getBytes("ISO-8859-1"),"utf-8");
	}
	public void init() throws ServletException {
		pageSize=Integer.parseInt(getServletConfig().getInitParameter("pageSize"));//获得初始化参数
		try {
			dbService=new DBServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
