package store;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader 
{
	private static Properties ps;
	static
	{
		ps=new Properties();
		InputStream in=PropertyReader.class.getResourceAsStream("db.conf");
		try 
		{
			ps.load(in);
			in.close();
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		
	}
	public static String get(String key)
	{
		return (String)ps.get(key);
	}
}


