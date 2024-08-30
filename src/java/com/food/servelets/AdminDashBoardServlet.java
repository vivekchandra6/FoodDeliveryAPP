package com.food.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooddelivery.daoImpl.RestaurantDaoImpl;
import com.fooddilivery.module.Restaurant;
import com.fooddilivery.module.User;

@WebServlet("/AdminDashBoard")
public class AdminDashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantDaoImpl restaurantDao;

	public void init(ServletConfig config) throws ServletException {
		restaurantDao = new RestaurantDaoImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User admin = (User) session.getAttribute("admin");
		if (admin != null) {
			List<Restaurant> restaurantList = restaurantDao.getRestaurantsByAdmin(admin.getUserID());
			request.setAttribute("restaurantList", restaurantList);
		}
		request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
	}
}