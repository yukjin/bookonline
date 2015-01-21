package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.DBService;
import store.DBServiceImpl;

public class NewBook extends HttpServlet {
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
		Pagination pagination;//分页类
		pagination=new Pagination(pageSize,currPage);
		try {
			
			pagination.dbService=dbService;
			BookInfo[] books=pagination.getBookInfoByNew();
			int pageNum=pagination.getPageNumByNewBooks();//获得新书查询结果共有多少页
			request.setAttribute("pageNum",pageNum);
			request.setAttribute("currPage",currPage);
			request.setAttribute("bookList",books);
			String path="/newBook.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//请求转发至category.jsp来显示查询结果
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		pageSize=Integer.parseInt(getServletConfig().getInitParameter("pageSize"));
		try {
			dbService=new DBServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
