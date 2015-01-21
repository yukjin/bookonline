package store;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool
{
	/**
	 * ���ӳؽӿڣ�����������ȡ�����ӡ��ͷ����Ӻ͹ر����ӵķ���*/
	/**�����ӳ���ȡ������*/
	public Connection getConnection()throws SQLException;
	/**�����ӷŻ����ӳ�*/
	public void releaseConnection(Connection con)throws SQLException;
	/**�ر����ӳ�*/
	public void close();
}
