package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.DBService;
import store.DBServiceImpl;

public class Score extends HttpServlet {
	DBService dbService;//数据库服务代理
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String bookid=request.getParameter("bookId");
		String bookRank=request.getParameter("bookRank");
		if(bookid==null||bookRank==null)
		{
			out.println("评价失败");
		}
		else
		{
			try 
			{
				if(dbService.scoreToBook(bookid,Integer.parseInt(bookRank)))
				{
					out.println("感谢您的评价！");
				}
				else
				{
					out.println("评价失败");
				}
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void init() throws ServletException {
		try {
			dbService=new DBServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
