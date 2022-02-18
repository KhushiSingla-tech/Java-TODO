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

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		TodoDao td = new TodoDao(GetConnection.getCon());
		boolean f = td.deleteTodo(id);
		
		HttpSession session = request.getSession();

		if(f)
		{
			session.setAttribute("msg", "Todo Deleted Successfully");
			response.sendRedirect("index.jsp");
		}
		else
		{
			session.setAttribute("msgfail", "Error");
			response.sendRedirect("index.jsp");
		}
	}

}
