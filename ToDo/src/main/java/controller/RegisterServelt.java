package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.GetConnection;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class RegisterServelt
 */
@WebServlet("/register")
public class RegisterServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("authcode");
		
		UserDao ud = new UserDao(GetConnection.getCon());
		boolean f = ud.addUser(user.getName(), user.getEmail(), user.getPassword());
		
		if(f)
		{
			session.setAttribute("msg", "Added Successfully");
			session.setAttribute("auth", user.getEmail());
			session.setAttribute("id", ud.getId(user.getEmail()));
			response.sendRedirect("index.jsp");
		}
		else
		{
			session.setAttribute("msgfail", "Error, Try Again!");
			response.sendRedirect("user.jsp");
		}
		
	}

}
