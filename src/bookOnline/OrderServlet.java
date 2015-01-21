package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		Cart cart=(Cart)session.getAttribute("cart");
		User user=(User)session.getAttribute("user");
		if(cart.getTotalNumber()==0)
		{
			request.setAttribute("result","订单为空订单提交失败！");
		}
		else
		{
			OrderService orderService=new OrderServiceImpl();
			Order[] order=orderService.CreateOrder(cart, user);//生成订单
			boolean success;
			try {
					if(orderService.submit(order))
					{
						cart.clear();//清空购物车
						request.setAttribute("result","订单提交成功！");
					}
					else
					{
					request.setAttribute("result","订单提交失败！");
					}
		    	} catch (SQLException e) 
		    	{
		    		  request.setAttribute("result","订单提交失败！");
		    		  e.printStackTrace();
		    	}
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("/OrderResult.jsp");
		dispatcher.forward(request, response);
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
