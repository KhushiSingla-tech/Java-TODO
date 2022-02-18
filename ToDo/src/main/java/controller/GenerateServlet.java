package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SendEmail;
import model.User;

/**
 * Servlet implementation class AuthenticateServlet
 */
@WebServlet("/generate")
public class GenerateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter())
		{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			
			SendEmail sm = new SendEmail();
			String code = sm.getRandom();
			
			User user = new User(name, email, pass, code);
			
			boolean test = sm.sendEmail(user);
			
			if(test)
			{
				HttpSession session = request.getSession();
				session.setAttribute("authcode", user);
				response.sendRedirect("verify.jsp");
			}
			else
			{
				out.println("Failed to send verfication email");
			}
		}
		
	}

}
