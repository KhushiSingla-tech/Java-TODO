package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	
	private Connection conn;
	String sql = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public UserDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean addUser(String name, String email, String password)
	{
		boolean f = false;
		try
		{
			sql = "insert into user(Name, Email, Password) values(?,?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			
			int i = ps.executeUpdate();
			
			if(i == 1)
			{
				f = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean auth(String email, String password)
	{
		boolean f = false;
		
		try
		{
			sql = "select Email, Password from user where email=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if(rs.next())
			{
				f = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}

	public boolean checkDuplicate(String email) {

		boolean f = false;
		
		try
		{
			sql = "select Email from user where Email=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			if(rs.next())
			{
				f = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
}
