package com.fooddilivery.module;

public class OrderItem {

	private int orderIteamId;
	private String orderId;
	private int menuId;
	private int quantity;
	private double totalPrice;
	private Menu menu;


	public OrderItem() {
	}

	public OrderItem(int orderIteamId, String orderId, int menuId, int quantity, double totalPrice) {
		super();
		this.orderIteamId = orderIteamId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public int getOrderIteamId() {
		return orderIteamId;
	}

	public void setOrderIteamId(int orderIteamId) {
		this.orderIteamId = orderIteamId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	   public Menu getMenu() {
	        return menu;
	    }

	    public void setMenu(Menu menu) {
	        this.menu = menu;
	    }
	    
	


	@Override
	public String toString() {
		return "OrderItem [orderIteamId=" + this.orderIteamId + ", orderId=" + this.orderId + ", menuId=" + this.menuId
				+ ", quantity=" + this.quantity + ", totalPrice=" + this.totalPrice + "]";
	}





}
