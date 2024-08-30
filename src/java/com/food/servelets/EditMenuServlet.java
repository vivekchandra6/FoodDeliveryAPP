package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.MenuDaoImpl;
import com.fooddilivery.module.Menu;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuDaoImpl menuImp;

    @Override
    public void init() throws ServletException {
        menuImp = new MenuDaoImpl();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Integer menuId = Integer.parseInt(request.getParameter("menuId"));
        Menu menu = menuImp.getMenuById(menuId);

        
        if (menu != null) {
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("editMenu.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Menu item not found.");
            request.getRequestDispatcher("menusById.jsp").forward(request, response);
        }
	}

}
