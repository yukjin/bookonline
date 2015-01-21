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
	DBService dbService;//���ݿ�������
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
		Pagination pagination;//��ҳ��
		pagination=new Pagination(pageSize,currPage);
		try {
			
			pagination.dbService=dbService;
			BookInfo[] books=dbService.autoCompleteSearch(bookname, currPage, pageSize);
			int pageNum=pagination.getPageNumByAutoSearch(bookname);//��ò�ѯ������ж���ҳ
			request.setAttribute("pageNum",pageNum);
			request.setAttribute("currPage",currPage);
			request.setAttribute("bookList",books);
			request.setAttribute("bookname",bookname);
			String path="/searchresult.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//����ת����searchresult.jsp����ʾ��ѯ���
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
