package com.fooddivery.dao;

import java.util.List;

import com.fooddilivery.module.Menu;

public interface MenuDao {
	void addMenu(Menu menu);
	Menu getMenuById(int menuId);
	boolean updateMenu(Menu menu);
	boolean deleteMenu(int menuId);
	List<Menu> getAllMenusByRestaurant(int restaurantId);


}
