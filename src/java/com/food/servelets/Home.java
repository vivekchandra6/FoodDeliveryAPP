package com.food.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.RestaurantDaoImpl;
import com.fooddilivery.module.Restaurant;
@WebServlet("/home")
public class Home extends HttpServlet {

	private static final long serialVersionUID = 6892730836947951352L;
	private RestaurantDaoImpl restaurantDao;
	@Override
	public void init() throws ServletException {
		restaurantDao = new RestaurantDaoImpl();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 try {
	            List<Restaurant> restaurants = restaurantDao.getAllRestaurant();
	            
	            request.setAttribute("restaurantList", restaurants);
	            
//	            response.sendRedirect("home.jsp");
	            
	            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	            rd.forward(request, response);
	        } catch (Exception e) {
	            // Log the error
	            System.err.println("Error in Home Servlet: " + e.getMessage());
	            // Set error attribute and forward to error page
	            request.setAttribute("error", e.getMessage());
	            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
	            rd.forward(request, response);
	        }
	    }
}



