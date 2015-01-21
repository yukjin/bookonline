package bookOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import store.ConnectionPool;
import store.ConnectionPoolImpl;

public class LoginServiceImpl implements LoginServices{
	private ConnectionPool pool;
	private static final String AUTHEN_SQL="select*from userinfo where username=? and password=?"; 
	public  static final String ERROR="<center><font color='ff0000'>用户名或密码错误！</font></center>";
	public  static final String SUCCESS="success";
	public LoginServiceImpl()
	{
		pool=new ConnectionPoolImpl();
	}
	public User authenticate(String username, String password)
	{
		try 
		{
			Connection conn=pool.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(AUTHEN_SQL);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				User user=new User();
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUserState(rs.getInt("userstate"));
				return user;
			}
			return null;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void rememberOrNot(HttpSession session,String remember) 
	{
		if(remember!=null)
		{
			session.setMaxInactiveInterval(-1);
		}
	}
}
