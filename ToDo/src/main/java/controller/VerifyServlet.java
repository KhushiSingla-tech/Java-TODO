package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter())
		{
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("authcode");
			
			String code = request.getParameter("authcode");
			
			if(code.equals(user.getCode()))
			{
				out.println("Verification Done");
				RequestDispatcher rd = request.getRequestDispatcher("register");
				rd.forward(request,response);
			}
			else
			{
				out.println("Incorrect Verification Code");
			}
		}
		
	}

}
