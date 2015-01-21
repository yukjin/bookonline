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

public class HotBook extends HttpServlet {
	DBService dbService;//���ݿ�������
	private int pageSize;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String curr=(String)request.getParameter("page");
		int currPage;
		if(curr==null)
		{
			currPage=1;
		}
		else	
		{
			currPage=Integer.parseInt((String)request.getParameter("page"));
		}
		Pagination pagination;//��ҳ��
		pagination=new Pagination(pageSize,currPage);
		try {
			
			pagination.dbService=dbService;
			BookInfo[] books=pagination.getBookInfoByHot();
			int pageNum=pagination.getPageNumByHotBooks();//��������ѯ������ж���ҳ
			request.setAttribute("pageNum",pageNum);
			request.setAttribute("currPage",currPage);
			request.setAttribute("bookList",books);
			String path="/index.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//����ת����category.jsp����ʾ��ѯ���
			dispatcher.forward(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		pageSize=Integer.parseInt(getServletConfig().getInitParameter("pageSize"));//��ó�ʼ������
		try {
			dbService=new DBServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
