package com.food.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.MenuDaoImpl;
import com.fooddelivery.daoImpl.OrderItemDaoImpl;
import com.fooddilivery.module.Menu;
import com.fooddilivery.module.Order;
import com.fooddilivery.module.OrderItem;
import com.fooddivery.dao.MenuDao;
import com.fooddivery.dao.OrderItemDao;

/**
 * Servlet implementation class OrderItems
 */
@WebServlet("/orderItems")
public class OrderItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderItemDao orderItemDao ;
	private MenuDao menuDao;

	@Override
	public void init() throws ServletException {
		orderItemDao  = new OrderItemDaoImpl();
		menuDao = new MenuDaoImpl();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");

		if("KnowMore".equals(action)) {
	            // Retrieve the order and order items from the session
	            Order order = (Order) request.getSession().getAttribute("order");

	            if (order != null) {
	                List<OrderItem> orderItems = orderItemDao.getOrderItemsByOrder(order.getOrderId());
	                
	                for (OrderItem orderItem : orderItems) {
				       Menu menuById = menuDao.getMenuById(orderItem.getMenuId());
	                	orderItem.setMenu(menuById);
					}
	                request.setAttribute("itemorderList", orderItems);
	            }}
			// Forward to the order confirmation page
			request.getRequestDispatcher("OrderedItems.jsp").forward(request, response);
		}
	}


