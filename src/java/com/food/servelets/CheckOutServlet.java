package com.food.servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooddelivery.daoImpl.OrderDaoImpl;
import com.fooddelivery.daoImpl.OrderHistoryDaoImpl;
import com.fooddelivery.daoImpl.OrderItemDaoImpl;
import com.fooddilivery.module.Cart;
import com.fooddilivery.module.CartItem;
import com.fooddilivery.module.Order;
import com.fooddilivery.module.OrderItem;
import com.fooddilivery.module.User;
import com.fooddivery.dao.OrderHistoryDao;
import com.fooddivery.dao.OrderItemDao;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDaoImpl orderDao;
	private OrderItemDao orderItemDao;
	private OrderHistoryDao orderHistoryDao;
	private Order order;

	@Override
	public void init() {
		orderDao = new OrderDaoImpl();
		orderItemDao = new OrderItemDaoImpl();
		orderHistoryDao = new OrderHistoryDaoImpl();
		order = new Order();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("loggedInUser");

		// Validate cart and user
		if (cart == null || cart.getItems().isEmpty()) {
			request.setAttribute("cartErrormessage", "Your cart is empty. Please select items before proceeding.");
			request.getRequestDispatcher("MenuServlete").forward(request, response);
			return;
		}
		
		if (user == null) {
			response.sendRedirect("login1.jsp");
			return;
		}

		// Proceed with the checkout
		if (cart != null && user != null && !cart.getItems().isEmpty()) {
			String paymentMode = request.getParameter("paymentMode");
			
			// Validate restaurantId
			Integer restaurantId = (Integer) session.getAttribute("restaurantId");
			if (restaurantId == null) {
				response.sendRedirect("restaurants.jsp");
				return;
			}

			// Set order details
			order.setUserId(user.getUserID());
			order.setRestuarantId(restaurantId);
			order.setOrderDate(new Date());
			order.setPayementMode(paymentMode);
			order.setStatus("Pending");

			// Calculate total amount
			double totalAmount = 0;
			for (CartItem item : cart.getItems().values()) {
				totalAmount += item.getQuantity() * item.getPrice();
			}
			order.setTotalAmount(totalAmount);

			// Add order to the database
			orderDao.addOrder(order);

			// Add order items to the database
			List<OrderItem> orderItemList = new ArrayList<>();
			for (CartItem cartItem : cart.getItems().values()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderId(order.getOrderId());
				orderItem.setMenuId(cartItem.getItemId());
				orderItem.setQuantity(cartItem.getQuantity());
				orderItem.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());
				orderItemList.add(orderItem);
				orderItemDao.addOrderItem(orderItemList);
			}

			// Add to order history
			orderHistoryDao.addOrderHistory(order);
//			System.out.println("Order placed successfully. Redirecting to Order Confirmation.");

			// Set session attributes for success and order details
			session.setAttribute("success", "Order successfully placed");
			session.setAttribute("order", order);
			session.setAttribute("orderItems", cart.getItems().values());

			// Clear the cart
			session.removeAttribute("cart");

			// Redirect to order confirmation page
			response.sendRedirect("OrderConformation.jsp");
		} else {
			response.sendRedirect("cart.jsp");
		}
	}
}
