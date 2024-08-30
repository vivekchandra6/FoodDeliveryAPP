package com.food.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.MenuDaoImpl;
import com.fooddilivery.module.Menu;

/**
 * Servlet implementation class ViewMenuServlet
 */
@WebServlet("/ViewMenus")
public class ViewMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   MenuDaoImpl menuImp;
       
	public void init(ServletConfig config) throws ServletException {
		menuImp = new MenuDaoImpl();
	}

	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			 doGet(req, resp);
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
		Integer resId = Integer.parseInt(request.getParameter("restaurantId"));
        List<Menu> allMenusByRestaurant = menuImp.getAllMenusByRestaurant(resId);
		
		 if(allMenusByRestaurant != null && !allMenusByRestaurant.isEmpty()) {
		      
			 request.setAttribute("menusByResId", allMenusByRestaurant);
			 request.getRequestDispatcher("menusById.jsp").forward(request, response);
			 
		 }else {
			 request.setAttribute("error", "No menus found for this restaurant.");
             request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
         }
        }catch (NumberFormatException e) {
	            request.setAttribute("error", "Invalid Restaurant ID format.");
	            request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
	        }
	}

}
