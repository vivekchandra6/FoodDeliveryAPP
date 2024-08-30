package com.fooddivery.dao;

import java.util.List;

import com.fooddilivery.module.OrderItem;

public interface OrderItemDao {
	void addOrderItem(List<OrderItem> orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getOrderItemsByOrder(String orderId);






}
