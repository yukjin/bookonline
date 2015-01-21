package bookOnline;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.ConnectionPool;
import store.ConnectionPoolImpl;

public class Register extends HttpServlet {
	ConnectionPool pool;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("txtUserName");
		try {
			Connection conn=pool.getConnection();
			if(request.getParameter("txtEmail")==null)
			{
				PreparedStatement pstmt=conn.prepareStatement("select username,password from userinfo where username=?");
				pstmt.setString(1,username);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next())
				{
					out.print("<font color='#FF0000'>用户名已存在</font>");
				}	
				else
				{
				out.print("<font color='#00CCCC'>账号无人使用</font>");
				}
				rs.close();
				conn.close();
			}
			else
			{
				conn=pool.getConnection();
				String password=request.getParameter("txtPswd");
				String email=request.getParameter("txtEmail");
				String phone=request.getParameter("txtPhone");
				String profession=request.getParameter("txtProfession");
				String address=request.getParameter("txtAddr");		
				User user=new User(username,password,email,address,1,profession);
				StringBuffer registerSql=new StringBuffer();
				registerSql.append("insert into userinfo(userid,username,password,email,phone,address,userstate,profession) ");
				registerSql.append("values(0,?,?,?,?,?,1,?)");
				PreparedStatement pstmt=conn.prepareStatement(registerSql.toString());
				pstmt.setString(1,user.getUsername());
				pstmt.setString(2,user.getPassword());
				pstmt.setString(3,user.getEmail());
				pstmt.setString(4,user.getPhone());
				pstmt.setString(5,user.getAddress());
				pstmt.setString(6,user.getProfession());
				System.out.println(3);
				pstmt.executeUpdate();//执行
			}
		} catch (SQLException e)
		{
			out.print("失败");
			e.printStackTrace();
		}finally
		{
			
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		pool=new ConnectionPoolImpl();
	}

}
