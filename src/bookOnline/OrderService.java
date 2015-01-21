package bookOnline;

import java.sql.SQLException;
import java.util.HashMap;

public interface OrderService 
{
	public boolean submit(Order[] order)throws SQLException;
	public Order[] CreateOrder(Cart cart,User user);
	public HashMap<String,String> orderStateQuery(User user);
}
