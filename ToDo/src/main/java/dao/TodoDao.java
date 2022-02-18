package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ToDo;

public class TodoDao {
	
	private Connection conn;
	String sql = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public TodoDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean addTodo(String name, String todo, String status, int uid)
	{
		boolean f = false;
		try
		{
			sql = "insert into todo(name, todo, status, UID) values(?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, todo);
			ps.setString(3, status);
			ps.setInt(4, uid);
			
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
	
	public List<ToDo> getTodo(int uid)
	{
		List<ToDo> list = new ArrayList<ToDo>();
		ToDo toDo = null;
		
		try
		{
			sql = "select * from todo where UID=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				toDo = new ToDo();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setTodo(rs.getString(3));
				toDo.setStatus(rs.getString(4));
				list.add(toDo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public ToDo getTodoById(int id)
	{
		ToDo toDo = null;
		
		try
		{
			sql = "select * from todo where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				toDo = new ToDo();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setTodo(rs.getString(3));
				toDo.setStatus(rs.getString(4));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return toDo;
	}
	
	public boolean updateTodo(ToDo toDo)
	{
		boolean f = false;
		try
		{
			sql = "update todo set name=?, todo=?, status=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, toDo.getName());
			ps.setString(2, toDo.getTodo());
			ps.setString(3, toDo.getStatus());
			ps.setInt(4, toDo.getId());
			
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
	
	public boolean deleteTodo(int id)
	{
		boolean f = false;
		
		try
		{
			sql = "delete from todo where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
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

}
