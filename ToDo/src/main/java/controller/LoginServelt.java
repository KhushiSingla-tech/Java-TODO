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

/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/login")
public class LoginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		UserDao ud = new UserDao(GetConnection.getCon());
		boolean f = ud.auth(email, pass);
		
		HttpSession session = request.getSession();
		
		if(f)
		{
			session.setAttribute("msg", "Login Successfully");
			session.setAttribute("auth", email);
			response.sendRedirect("index.jsp");
		}
		else
		{
			session.setAttribute("msgfail", "Either Username or Password is wrong. Try Again!");
			response.sendRedirect("user.jsp");
		}
		
		
	}

}
