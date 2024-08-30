package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PasswordReset
 */
//url coming from user email... 
@WebServlet("/resetPassword")
public class PasswordReset extends HttpServlet {



	private static final long serialVersionUID = 1L;
  @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  
		String token = request.getParameter("token");

		if(token != null && !token.isEmpty()) {
            // Store the token in the session for not visible in url
			request.getSession().setAttribute("resettoken", token);
		    response.sendRedirect("resetPassword.jsp");
		}else {
			response.sendRedirect("errorPage.jsp");
		}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doGet(request, response);
	}

}
