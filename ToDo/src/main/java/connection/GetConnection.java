package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
	
	private static Connection conn;
	
	public static Connection getCon()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}

}
