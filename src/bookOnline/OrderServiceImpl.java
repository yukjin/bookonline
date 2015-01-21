package bookOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import store.ConnectionPool;
import store.ConnectionPoolImpl;

public class OrderServiceImpl implements OrderService
{
	private static final String INSERT_ORDER="insert into trade values(0,?,?,?,?,NOW(),?,?,?)";
	private ConnectionPool pool;//连接池
	public Order[] CreateOrder(Cart cart,User user)
	{
		int books=cart.getBooks().size();
		HashMap<BookInfo, Integer> booksMap=cart.getBooks();
		
		Order[] order=new Order[books];
		String orderNumber=String.valueOf(new Date().getTime());
		for(int i=0;i<books;i++)
		{
			order[i]=new Order();
			order[i].setTradeNumber(orderNumber);
			order[i].setUserId(user.getUserId());
			order[i].setTradeTime(new Date());
			order[i].setTradeState(0);
			order[i].setTradeAddress(user.getAddress());
		}
		Set<Entry<BookInfo,Integer>> entrySet= booksMap.entrySet();
		Iterator<Entry<BookInfo,Integer>> iter=entrySet.iterator();
		int i=0;
		while(iter.hasNext())
		{
			Entry<BookInfo,Integer> entry=iter.next();
			BookInfo book=entry.getKey();
			int booknum=entry.getValue();
			order[i].setBookId(book.getBookid());
			order[i].setTradeMoney(book.getBookprice()*booknum);
			order[i].setTradeNum(booknum);
			i++;
		}
		
		return order;
	}

	public boolean submit(Order[]order) throws SQLException
	{
		pool=new ConnectionPoolImpl();
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
				conn=pool.getConnection();
				conn.setAutoCommit(false);
				pstmt=conn.prepareStatement(INSERT_ORDER);
				for(int i=0;i<order.length;i++)
				{
					pstmt.setInt(1,order[i].getUserId());
					pstmt.setString(2,order[i].getBookId());
					pstmt.setInt(3,order[i].getTradeNum());
					pstmt.setDouble(4,order[i].getTradeMoney());
					pstmt.setInt(5,order[i].getTradeState());
					pstmt.setString(6,order[i].getTradeNumber());
					pstmt.setString(7,order[i].getTradeAddress());
					pstmt.executeUpdate();
				}
				conn.commit();
				return true;
		   } catch (SQLException e) {
				conn.rollback();
			   return false;
		   }
		
	}

	public HashMap<String,String> orderStateQuery(User user) 
	{
		pool=new ConnectionPoolImpl();
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			String sql="select tradenumber,tradestate from userinfo,trade where userinfo.userid=trade.userid and trade.userid=?";
			conn=pool.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,user.getUserId());
			HashMap<String,String> orderMap=new HashMap<String,String>();
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String tradeState="";
				if(rs.getString("tradestate").equals("0"))
				{
					tradeState="尚未发货";
				}
				else if(rs.getString("tradestate").equals("1"))
				{
					tradeState="已发货";
				}
				else if(rs.getString("tradestate").equals("2"))
				{
					tradeState="交易成功";
				}
				orderMap.put(rs.getString("tradenumber"),tradeState);
			}
			return orderMap;
		} catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return null;
		
		
		
	}

}
