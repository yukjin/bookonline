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
	/**�����ӳ���ȡ������*/
	public Connection getConnection() throws SQLException 
	{
		synchronized(pool)//����ͬ�����������ӳ�
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
	

	/**�����ӷŻ����ӳ�*/
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
	protected void finalize()//��������ʱ���ܻ����
	{
		close();
	}
	/**�ر����ӳ�*/
	public void close()
	{
		Iterator<Connection> iter=pool.iterator();
		while(iter.hasNext())
		{
			try{
				closeJdbcConnection(iter.next());//�ر����������ݿ�����
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		pool.clear();
	}
	/**���ض�̬���Ӵ���*/
	private Connection getProxy(final Connection con,final ConnectionPool pool)
	{
		InvocationHandler handle=new InvocationHandler()
		{

			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if(method.getName().equals("close"))
				{
					//System.out.println("������"+proxy.getClass().getName()+"����releaseConnection()");
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
		public Connection getJdbcConnection();//���ر������Connection����
	}

}

