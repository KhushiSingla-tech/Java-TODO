package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.GetConnection;
import dao.TodoDao;
import dao.UserDao;

/**
 * Servlet Filter implementation class AuthEmail
 */
@WebFilter("/generate")
public class AuthEmail implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		String email = req.getParameter("email");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		UserDao ud = new UserDao(GetConnection.getCon());
		boolean test = ud.checkDuplicate(email);
		
		HttpSession session = request.getSession();
		
		if(test)
		{
			session.setAttribute("msgfail", "Email Already Registered");
			response.sendRedirect("user.jsp");
		}
		else
		{
			chain.doFilter(request, response);
		}
	}



}
