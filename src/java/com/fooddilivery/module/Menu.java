package com.fooddilivery.module;

public class Menu {
	private int menuId;
	private int restuarantId;
	private String itemName;
	private String description;
	private double price;
	private double rating;
	private String isAvailable;
	private String imagePath;

	public Menu() {
		// TODO Auto-generated constructor stub
	}
	

	public Menu(int menuId, int restuarantId, String itemName, String description, double price, double rating,
			String isAvailable) {
		super();
		this.menuId = menuId;
		this.restuarantId = restuarantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
	}


	public Menu(int menuId, int restuarantId, String itemName, String description, double price, double rating,
			String  isAvailable, String imagePath) {
		super();
		this.menuId = menuId;
		this.restuarantId = restuarantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestuarantId() {
		return restuarantId;
	}

	public void setRestuarantId(int restuarantId) {
		this.restuarantId = restuarantId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String isAvailable() {
		return isAvailable;
	}

	public void setAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


}
