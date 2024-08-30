package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.MenuDaoImpl;


/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;		
	private MenuDaoImpl menuImp;
	    @Override
	    public void init() throws ServletException {
	        menuImp = new MenuDaoImpl();
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer menuId = Integer.parseInt(request.getParameter("menuId"));

		 boolean isDeleted = menuImp.deleteMenu(menuId);
		
		 if (isDeleted) {
	            request.setAttribute("success", "Menu item deleted successfully.");
	        } else {
	            request.setAttribute("error", "Failed to delete menu item.");
	        }

	        // Reload the menu list after deletion
	        Integer resId = Integer.parseInt(request.getParameter("restaurantId"));
	        request.getRequestDispatcher("/ViewMenus?restaurantId=" + resId).forward(request, response);
	}

}
