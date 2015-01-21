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

public class AutoComplete extends HttpServlet {
	DBService dbService;//数据库服务代理
	
	public void destroy()
	{
		super.destroy(); 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String bookname=request.getParameter("bookname");
		try
		{
			out.print(dbService.autoCompleteSearch(bookname));
		} catch (SQLException e) {
			e.printStackTrace();
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
