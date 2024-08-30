package com.fooddilivery.module;

import java.util.Date;
import java.util.List;

public class Order {
	private String orderId;
	private int userId;
	private int restuarantId;
	private Date orderDate;
	private double totalAmount;
	private String status;
	private String payementMode;
	private List<OrderItem> orderItems;


	public Order() {
		// TODO Auto-generated constructor stub
	}


	public Order(String orderId, int userId, int restuarantId, Date orderDate, double totalAmount, String status,
			String payementMode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restuarantId = restuarantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.payementMode = payementMode;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getRestuarantId() {
		return restuarantId;
	}


	public void setRestuarantId(int restuarantId) {
		this.restuarantId = restuarantId;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPayementMode() {
		return payementMode;
	}


	public void setPayementMode(String payementMode) {
		this.payementMode = payementMode;
	}
	
	public List<OrderItem> getOrderItems() 
	{ 
		return orderItems;
	}
    public void setOrderItems(List<OrderItem> orderItems) 
    { 
    	this.orderItems = orderItems; 
    }





}
