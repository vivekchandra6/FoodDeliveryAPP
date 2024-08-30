package com.food.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.MenuDaoImpl;
import com.fooddilivery.module.Menu;

@WebServlet("/MenuServlete")
public class MenuServlete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MenuDaoImpl  menuDao;

	@Override
	public void init() throws ServletException {
		menuDao	= new MenuDaoImpl();
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		
			List<Menu> menuList = menuDao.getAllMenusByRestaurant(restaurantId);
                  try { 
                	   if (!menuList.isEmpty() || menuList != null) {

                		   request.setAttribute("menuList", menuList);
                		   
                		   
                		   RequestDispatcher rd = request.getRequestDispatcher("menus.jsp");
                		   rd.forward(request, response);
                	   }
                  } catch (Exception e) {
                	//log the error
                	  System.err.println("Error in Menu Servlet: " + e.getLocalizedMessage());
      	            // Set error attribute and forward to error page
                	  request.setAttribute("error", e.getMessage());
                	  RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                	  rd.forward(request, response);
                  }
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
	}
}
