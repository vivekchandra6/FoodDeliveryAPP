package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooddelivery.daoImpl.UserDaoImpl;
import com.fooddilivery.module.User;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDaoImpl userDao;
	public void init() throws ServletException {
	    userDao = new UserDaoImpl();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		           HttpSession session = request.getSession();
		
		User user =(User) session.getAttribute("loggedInUser");
		User admin = (User)session.getAttribute("admin");
		 
		 if(user != null || admin != null) {
			 
			 	String name = request.getParameter("name");
			 	String username = request.getParameter("Username");
	            String email = request.getParameter("email");
	            Long phone = Long.parseLong(request.getParameter("phone"));
	            String address = request.getParameter("address");
	            int Id = Integer.parseInt(request.getParameter("Id"));
	            
	            User currentUser = (user != null) ? user : admin;

	            currentUser.setName(name);
	            currentUser.setUserName(username);
	            currentUser.setEmail(email);
	            currentUser.setPhoneNum(phone);
	            currentUser.setAdress(address);
	            currentUser.setUserID(Id);
	            System.out.println(currentUser.toString());
	            userDao.updateUser(currentUser);
	            // Update the session attribute with the updated user object
	            session.setAttribute("loggedInUser", currentUser);
		 
	            request.setAttribute("updated", "Details updated successfully");
	             request.getRequestDispatcher("profile.jsp").forward(request, response);
		 }else {
			  response.sendRedirect("login1.jsp");
		 }
		 
		 
	}

}
