package Validation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Verification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getHeader("user");
		String password = request.getHeader("pass");

		Credentials cr = new Credentials(userName,password);
		
		boolean status=cr.verifyCredentials();
		
		if(status==true) {
			
			String key=cr.generateKey();
			cr.updateTable(key, userName);
			request.setAttribute("key", key);
			request.setAttribute("user", userName);
			request.setAttribute("cred", cr);
			RequestDispatcher rd=request.getRequestDispatcher("/my-servlet");
			rd.forward(request, response);
		}
		else {
			
			RequestDispatcher rd=request.getRequestDispatcher("/response.jsp");
			rd.forward(request, response);
			
		}

	}

}
