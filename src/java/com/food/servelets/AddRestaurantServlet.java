package com. food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooddelivery.daoImpl.RestaurantDaoImpl;
import com.fooddilivery.module.Restaurant;
import com.fooddilivery.module.User;

@WebServlet("/AddRestaurantServlet")
public class AddRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantDaoImpl resImp;
	@Override
	public void init() throws ServletException {
		resImp = new RestaurantDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User admin =(User) session.getAttribute("admin");

		if(admin == null) {
			response.sendRedirect("home");
		}

		int userID = admin.getUserID();

		String restaurantName = request.getParameter("restaurantName");
		String address= request.getParameter("address");
		String phone = request.getParameter("phone");
		Double rating= Double.parseDouble( request.getParameter("rating"));
		String cuisineType = request.getParameter("cuisineType");
		String isActive = request.getParameter("isActive");
		String eta = request.getParameter("eta");
		String imagePath = request.getParameter("imagePath");

		Restaurant res =  new Restaurant(restaurantName, address, phone, rating, cuisineType, isActive, eta, userID, imagePath);
		//  System.out.println("ResId: "+ res.getResturantId());
		boolean addedRes = resImp.addRestaurant(res);
		if(addedRes) {
			request.setAttribute("restaurantId", res.getResturantId()); 
			request.setAttribute("added", "Restaurant Added Successfully");
			request.getRequestDispatcher("AddRestaurant.jsp").forward(request, response);
		} else {
			request.setAttribute("failed", "Failed to add Restaurant");
			request.getRequestDispatcher("AddRestaurant.jsp").forward(request, response);
		}

	}

}
