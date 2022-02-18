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

@WebServlet("/add")
public class TodoServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String todo = request.getParameter("todo");
		String status = request.getParameter("status");
		
		HttpSession session = request.getSession();
		
		TodoDao td = new TodoDao(GetConnection.getCon());
		boolean f = td.addTodo(username, todo, status, (int) session.getAttribute("id"));
		
		if(f)
		{
			session.setAttribute("msg", "Todo Added Successfully");
			response.sendRedirect("index.jsp");
		}
		else
		{
			session.setAttribute("msgfail", "Error");
			response.sendRedirect("index.jsp");
		}
	}

}
