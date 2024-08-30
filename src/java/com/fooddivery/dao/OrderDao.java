package com.fooddivery.dao;

import java.util.List;

import com.fooddilivery.module.Order;

public interface OrderDao {


	void addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order> getAllOrderByUser(int userId);




}
