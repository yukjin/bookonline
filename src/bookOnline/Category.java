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
		int currPage=Integer.parseInt((String)request.getParameter("page"));
		String booktype=request.getParameter("booktype");
		Pagination pagination;//��ҳ��
		booktype=convert(booktype);
		pagination=new Pagination(booktype,pageSize,currPage);
		try {
			
			pagination.dbService=dbService;
			BookInfo[] books=pagination.getBookInfoByType();
			int pageNum=pagination.getPageNumByCategory();//��ò�ѯ������ж���ҳ
			request.setAttribute("pageNum",pageNum);//��ѯ���ҳ��
			request.setAttribute("currPage",currPage);//��ǰҳ
			request.setAttribute("bookList",books);//��request�д洢book��ѯ���
			request.setAttribute("booktype",booktype);//��request�д洢booktype
			String path="/category.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);//����ת����category.jsp����ʾ��ѯ���
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
		pageSize=Integer.parseInt(getServletConfig().getInitParameter("pageSize"));//��ó�ʼ������
		try {
			dbService=new DBServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
