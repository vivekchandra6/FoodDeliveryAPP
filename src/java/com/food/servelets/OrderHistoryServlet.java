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
import com.fooddelivery.daoImpl.OrderHistoryDaoImpl;
import com.fooddelivery.daoImpl.OrderItemDaoImpl;
import com.fooddilivery.module.Menu;
import com.fooddilivery.module.OrderHistory;
import com.fooddilivery.module.OrderItem;
import com.fooddilivery.module.User;
import com.fooddivery.dao.MenuDao;
import com.fooddivery.dao.OrderItemDao;

@WebServlet("/OrderHistory")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private OrderHistoryDaoImpl orderHistoryDao;
    private OrderItemDao orderItemDaoImpl;
    private MenuDao menuDaoImp;
    @Override
    public void init() throws ServletException {
            // Create an OrderHistory object
    	orderHistoryDao= new OrderHistoryDaoImpl();
    	 orderItemDaoImpl = new OrderItemDaoImpl();
    	 menuDaoImp = new MenuDaoImpl();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve relevant information from the session or request
    	User user = (User) request.getSession().getAttribute("loggedInUser");
        if (user != null ) {
        	List<OrderHistory> orderList = orderHistoryDao.getOrderHistoriesByUserId(user.getUserID());
        	
        	for (OrderHistory orderHistory : orderList) {
        		List<OrderItem> itemsByOrderId = orderItemDaoImpl.getOrderItemsByOrder(orderHistory.getOrderId());
        		for (OrderItem item : itemsByOrderId) {
                    Menu menu = menuDaoImp.getMenuById(item.getMenuId());
                    item.setMenu(menu); 
        	}
        		orderHistory.setOrderItems(itemsByOrderId);
        	}
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        } 
        else {
        	request.setAttribute("error", "Login to check OrderHistory....");
        	  RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
	          rd.forward(request, response);
        }
}
}
