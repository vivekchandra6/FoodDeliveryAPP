package com.food.servelets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooddelivery.daoImpl.MenuDaoImpl;
import com.fooddilivery.module.Cart;
import com.fooddilivery.module.CartItem;
import com.fooddilivery.module.Menu;


@WebServlet("/cart")
public class cartservlet extends HttpServlet {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//    	 System.out.println("doPost method called");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");


        if (cart == null) {
        	cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if (action.equals("AddtoCart")) {
            addItemToCart(request, cart);
        } else if (action.equals("Update")) {
            updateCartItem(request, cart);
        } else if (action.equals("Remove")) {
            removeItemFromCart(request, cart);
        }
        session.setAttribute("cart", cart);
//        System.out.println("Cart in session: " + session.getAttribute("cart"));
        response.sendRedirect("cart.jsp");
//        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }


    void addItemToCart(HttpServletRequest request, Cart cart) {
//    	System.out.println("addItemToCart method called");
        int itemId = Integer.parseInt(request.getParameter("itemId"));
//        System.out.println("Item ID: " + itemId);
        int quantity = Integer.parseInt(request.getParameter("quantity"));

         MenuDaoImpl menuDao = new MenuDaoImpl();
         Menu menuItem = menuDao.getMenuById(itemId);

        HttpSession session = request.getSession();
        
        session.setAttribute("restaurantId", menuItem.getRestuarantId());

        if (menuItem != null) {
            CartItem item = new CartItem(menuItem.getMenuId(), 
            		menuItem.getRestuarantId(),
                    menuItem.getItemName(), 
                    menuItem.getPrice(), quantity);
            		
            		cart.addItems(item);

//            System.out.println( cart.getItems());
//            System.out.println(itemId  +" "+ quantity);
//            System.out.println("Menu Item: " + menuItem);


        }
    }

    void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItem(itemId, quantity);
    }

    void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cart.removeItem(itemId);
    }
}