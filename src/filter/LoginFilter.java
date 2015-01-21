package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookOnline.Cart;
import bookOnline.LoginServiceImpl;
import bookOnline.LoginServices;
import bookOnline.User;

public class LoginFilter implements Filter
{
	LoginServices services;
	public void destroy() {
	
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			HttpSession session=((HttpServletRequest)request).getSession();
			
			if(session.getAttribute("cart")==null)
			{
				((HttpServletResponse)response).sendRedirect("hotbook.do");
				return;
			}
			filter.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		services=new LoginServiceImpl();
	}

}
