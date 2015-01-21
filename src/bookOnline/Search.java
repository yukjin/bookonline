package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.DBService;
import store.DBServiceImpl;

public class Search extends HttpServlet {
	DBService dbService;//数据库服务代理
	private int pageSize;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String curr=(String)request.getParameter("page");
		String bookname=(String)request.getParameter("bookname");
		bookname=new String(bookname.getBytes("iso-8859-1"),"utf-8");
		int currPage;
		if(curr==null)
		{
			currPage=1;
		}
		else	
		{
			currPage=Integer.parseInt((String)request.getParameter("page"));
		}
		Pagination pagination;//分页类
		pagination=new Pagination(pageSize,currPage);
		try {
			
			pagination.dbService=dbService;
			BookInfo[] books=dbService.autoCompleteSearch(bookname, currPage, pageSize);
			int pageNum=pagination.getPageNumByAutoSearch(bookname);//获得查询结果共有多少页
			request.setAttribute("pageNum",pageNum);
			request.setAttribute("currPage",currPage);
			request.setAttribute("bookList",books);
			request.setAttribute("bookname",bookname);
			String path="/searchresult.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//请求转发至searchresult.jsp来显示查询结果
			dispatcher.forward(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
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
