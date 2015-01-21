package store;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectionPoolImpl implements ConnectionPool{

	private ConnectionProvider provider=new ConnectionProvider();
	private final ArrayList<Connection> pool=new ArrayList<Connection>();
	private int poolSize=10;
	
	public ConnectionPoolImpl(){}
	public ConnectionPoolImpl(int poolSize)
	{
		this.poolSize=poolSize;
	}
	/**从连接池中取出连接*/
	public Connection getConnection() throws SQLException 
	{
		synchronized(pool)//采用同步锁控制连接池
		{
			if(!pool.isEmpty())
			{
				int last=pool.size()-1;
				Connection con=pool.remove(last);
				return con;
			}
		}
		Connection con=provider.getConnection();
		return getProxy(con,this);
	}
	

	/**把连接放回连接池*/
	public void releaseConnection(Connection con) throws SQLException {
		synchronized(pool)
		{
			int currSize=pool.size();
			if(currSize<poolSize)
			{
				pool.add(con);
				//System.out.println(con.getClass().getName());
				return;
			}
			
		}
		try{
			closeJdbcConnection(con);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	private void closeJdbcConnection(Connection con)throws SQLException{
		ConnectionP conP=(ConnectionP)con;
		conP.getJdbcConnection().close();
	}
	protected void finalize()//垃圾回收时可能会调用
	{
		close();
	}
	/**关闭连接池*/
	public void close()
	{
		Iterator<Connection> iter=pool.iterator();
		while(iter.hasNext())
		{
			try{
				closeJdbcConnection(iter.next());//关闭真正的数据库连接
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		pool.clear();
	}
	/**返回动态连接代理*/
	private Connection getProxy(final Connection con,final ConnectionPool pool)
	{
		InvocationHandler handle=new InvocationHandler()
		{

			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if(method.getName().equals("close"))
				{
					//System.out.println("代理类"+proxy.getClass().getName()+"调用releaseConnection()");
					pool.releaseConnection((Connection)proxy);
					return null;
				}
				else if(method.getName().equals("getJdbcConnection"))
				{
					return con;
				}
				else
				{
					return method.invoke(con, args);
				}
			}
			
		};
		return (Connection)Proxy.newProxyInstance(ConnectionP.class.getClassLoader(),new Class[]{ConnectionP.class},handle);
	}
	interface ConnectionP extends Connection
	{
		public Connection getJdbcConnection();//返回被代理的Connection对象
	}

}

