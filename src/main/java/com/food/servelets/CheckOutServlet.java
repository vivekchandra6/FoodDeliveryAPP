package com.food.servelets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooddelivery.daoImpl.OrderDaoImpl;
import com.fooddilivery.module.Cart;
import com.fooddilivery.module.CartItem;
import com.fooddilivery.module.Order;
import com.fooddilivery.module.User;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	OrderDaoImpl orderDao;
	Order order;
	@Override
	public void init() {
		 orderDao = new OrderDaoImpl();
		  order = new Order();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {

	HttpSession session = request.getSession();
	Cart cart =(Cart)session.getAttribute("cart");
	User user = (User)session.getAttribute("username");

	if(cart==null || cart.getItems().isEmpty() ) {
		 request.setAttribute("cartErrormessage", "Your cart is empty. Please select items before proceeding.");
         request.getRequestDispatcher("MenuServlete").forward(request, response);
	}
	else if(user==null){
		request.setAttribute("userErrorMessage", "You need to login to complete your order.");
        request.getRequestDispatcher("login1.jsp").forward(request, response);

	}
	else if(cart!=null && user!= null && !cart.getItems().isEmpty()) {
		String paymentMode = request.getParameter("paymentMode");
		order.setUserId(user.getUserID());
		order.setRestuarantId((int)session.getAttribute("restaurantId"));
		order.setOrderDate(new Date());
		order.setPayementMode(paymentMode);
		order.setStatus("Pending");

		double totalAmount=0;
		for(CartItem item :cart.getItems().values()) {
		totalAmount = totalAmount+item.getQuantity()* item.getPrice();
		}
		order.setTotalAmount(totalAmount);

		orderDao.addOrder(order);
		session.setAttribute("order", order);
		 session.setAttribute("orderItems", cart.getItems().values());
		response.sendRedirect("OrderConformation.jsp");
	}
	else {
		response.sendRedirect("cart.jsp");
	}

	session.removeAttribute("cart");



	}

}
