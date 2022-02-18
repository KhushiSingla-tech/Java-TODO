package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.GetConnection;
import dao.TodoDao;
import model.ToDo;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String todo = request.getParameter("todo");
		String status = request.getParameter("status");
		
		TodoDao td = new TodoDao(GetConnection.getCon());
		
		ToDo toDo = new ToDo();
		toDo.setId(id);
		toDo.setName(username);
		toDo.setTodo(todo);
		toDo.setStatus(status);
		
		boolean f = td.updateTodo(toDo);
		HttpSession session = request.getSession();
		
		if(f)
		{
			session.setAttribute("msg", "Todo Update Successfully");
			response.sendRedirect("index.jsp");
		}
		else
		{
			session.setAttribute("msgfail", "Error");
			response.sendRedirect("index.jsp");
		}
		
	}

}
