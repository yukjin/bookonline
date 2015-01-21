package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharacterFilter implements Filter
{
	private String encoding;
	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		long start=System.currentTimeMillis();
		chain.doFilter(request, response);
		long end=System.currentTimeMillis();
		System.out.println((end-start));
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		encoding=filterConfig.getInitParameter("encoding");//��ȡ���뷽ʽ��ʼ������
	}

}
