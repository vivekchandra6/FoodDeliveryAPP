package com.food.servelets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.UserDaoImpl;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/ForgotPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  UserDaoImpl userDaoImpl ;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userDaoImpl	=new  UserDaoImpl();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
//	    System.out.println("User email_Id: " + email);
		request.getSession().setAttribute("email", email);
	    
	    if (userDaoImpl.getByEmail(email)) {
	        String resetToken = userDaoImpl.generateAndStoreResetToken(email);
//	        System.out.println("reset Token: " + resetToken);
	        
	        if (resetToken != null) {
	            try {
	            	userDaoImpl.sendResetEmail(email, resetToken);
	                request.setAttribute("message", "password reset instructions have been sent successfully.");
	                request.setAttribute("messageType", "success");
	            } catch (AddressException e) {
	                e.printStackTrace();
	            } catch (MessagingException e) {
	                e.printStackTrace();
	            }
	        } else {
	            request.setAttribute("message", "Token is Expired. Please try again later.");
	            request.setAttribute("messageType", "warning");
	        }
	    } else {
	        request.setAttribute("message", "Email not found...Plzz Check");
	        request.setAttribute("messageType", "error");
	    }
	    
	    request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
	}

}
