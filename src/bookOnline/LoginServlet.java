package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	LoginServices services;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String remember=request.getParameter("remember");
		User user=services.authenticate(username, password);
		if(user!=null)//登陆成功
		{
			HttpSession session=request.getSession();
			Cart cart=new Cart();
			cart.setUser(user);
			session.setAttribute("user",user);//绑定用户session
			session.setAttribute("cart",cart);//在session中加入购物车
			out.print(LoginServiceImpl.SUCCESS);
		}
		else
		{
			out.print(LoginServiceImpl.ERROR);
		}
	}

	public void init() throws ServletException {
		services=new LoginServiceImpl();
	}

}
