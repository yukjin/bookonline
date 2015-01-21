package bookOnline;

import javax.servlet.http.HttpSession;

public interface LoginServices 
{
	public User authenticate(String username,String password);
	public void rememberOrNot(HttpSession session,String remember);
}
