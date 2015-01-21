package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderQuery extends HttpServlet {
private OrderService orderService;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		HashMap<String,String> orderMap=orderService.orderStateQuery(user);
		request.setAttribute("orderMap",orderMap);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/orderState.jsp");
		dispatcher.forward(request, response);
	}
	public void init() throws ServletException {
		orderService=new OrderServiceImpl();
	}

}
