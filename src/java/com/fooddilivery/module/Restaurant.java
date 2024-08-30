package com.fooddilivery.module;

public class Restaurant {

	private int resturantId;
	private String name;
	private String adress;
	private String phoneNum;
	private double rating;
	private String cousineType;
	private String isActive;
	private String eta;
	private int adminId;
	private String imagePath;


	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

           

	public Restaurant( String name, String adress, String phoneNum, double rating, String cousineType,
			String isActive, String eta, int adminId, String imagePath) {
		super();
		this.name = name;
		this.adress = adress;
		this.phoneNum = phoneNum;
		this.rating = rating;
		this.cousineType = cousineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminId = adminId;
		this.imagePath = imagePath;
	}


	public Restaurant(int resturantId, String name, String adress, String phoneNum, double rating, String cousineType,
			String isActive, String eta, int adminId, String imagePath) {
		super();
		this.resturantId = resturantId;
		this.name = name;
		this.adress = adress;
		this.phoneNum = phoneNum;
		this.rating = rating;
		this.cousineType = cousineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminId = adminId;
		this.imagePath = imagePath;
	}



	public int getResturantId() {
		return resturantId;
	}


	public void setResturantId(int resturantId) {
		this.resturantId = resturantId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getCousineType() {
		return cousineType;
	}


	public void setCousineType(String cousineType) {
		this.cousineType = cousineType;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getETA() {
		return eta;
	}


	public void setEta(String eta) {
		this.eta = eta;
	}


	public int getAdmineId() {
		return adminId;
	}


	public void setAdmineId(int adminId) {
		this.adminId = adminId;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}






}
