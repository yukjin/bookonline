package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider
{
	private String JDBC_DRIVER;
	private String DB_URL;
	private String DB_USER;
	private String DB_PASSWORD;
	public ConnectionProvider()
	{
		this.JDBC_DRIVER=PropertyReader.get("JDBC_DRIVER");
		this.DB_URL=PropertyReader.get("DB_URL");
		this.DB_USER=PropertyReader.get("DB_USER");
		this.DB_PASSWORD=PropertyReader.get("DB_PASSWORD");
		try
		{
			Class.forName(JDBC_DRIVER);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}
}

