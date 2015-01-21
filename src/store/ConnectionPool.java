package store;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool
{
	/**
	 * 连接池接口，其中声明了取出连接、释放链接和关闭连接的方法*/
	/**从连接池中取出连接*/
	public Connection getConnection()throws SQLException;
	/**把连接放回连接池*/
	public void releaseConnection(Connection con)throws SQLException;
	/**关闭连接池*/
	public void close();
}
