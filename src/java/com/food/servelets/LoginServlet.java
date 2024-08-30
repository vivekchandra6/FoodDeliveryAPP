package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.util.PasswordUtils;
import com.fooddelivery.daoImpl.UserDaoImpl;
import com.fooddilivery.module.User;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private  UserDaoImpl userDao ;
	 @Override
	public void init() throws ServletException {
		super.init();
		userDao= new UserDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("Email");
        String password = request.getParameter("password");

        User user = userDao.getUserByEmail(email);
//        System.out.println(user.toString());

        if (user != null) {
            // Debugging information
            System.out.println("Entered Password (Plain): " + password);
            System.out.println("Stored Password (Encrypted): " + user.getPassword());

            // Check if the entered password matches the stored encrypted password
            if (PasswordUtils.verifyPassword(password, user.getPassword())) {
                HttpSession session = request.getSession();
                String role = user.getRole();
                if ("Customer".equals(role)) {
                	session.setAttribute("loggedInUser", user);
                	response.sendRedirect("home");
				} else if("Restaurant_admin".equals(role)) {
                	session.setAttribute("admin", user);
                	response.sendRedirect("AdminDashBoard");
				}
                 session.removeAttribute("cart");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("home");
//                dispatcher.forward(request, response);
                return;
            }
        }
        request.setAttribute("errorMessage", "Invalid username or password");
        request.getRequestDispatcher("login1.jsp").forward(request, response);
    }
}
