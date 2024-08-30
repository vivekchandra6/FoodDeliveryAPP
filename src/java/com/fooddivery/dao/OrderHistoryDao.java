package com.fooddivery.dao;

import java.util.List;

import com.fooddilivery.module.Order;
import com.fooddilivery.module.OrderHistory;

public interface OrderHistoryDao {

	void addOrderHistory(Order order);
	OrderHistory getOrderHistory(int orderHistoryId);
	void updateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	List<OrderHistory> getOrderHistoriesByUserId(int userId);

}
