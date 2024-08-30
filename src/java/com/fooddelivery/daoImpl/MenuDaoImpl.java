
package com.fooddelivery.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.util.DBConnectionUtil;
import com.fooddilivery.module.Menu;
import com.fooddivery.dao.MenuDao;

public class MenuDaoImpl implements MenuDao {
	private  Connection connection =null;
	private static PreparedStatement prepareStatement =null;
	private ResultSet res =null;
	Statement statement = null;

	private final static String INSERT_QUERY = "insert into `menu`(`IdRestaurant`, `ItemName`, `Description`, `Price`, `Ratings`, `isAvailable`, `ImagePath`)values(?,?,?,?,?,?,?)";
	private final static String SELECT_QUERY ="select * from `menu`  where `MenuID`=?";
	private final static String DELETE_QUERY ="delete from `menu` where `MenuID`=? ";
	private final static String UPDATE_QUERY ="update `menu` set `Idrestaurant`=?, `ItemName`=?, `Description`=?,`Price`=?, `Ratings`=?,`isAvailable`=? where `MenuID`=? ";
	private final static String SELECT_ALL_QUERY ="select * from `menu` where `Idrestaurant`= ?";

	public MenuDaoImpl() {
		connection = DBConnectionUtil.getConnection();
	}
	@Override
	public void addMenu(Menu menu) {
		try {
			 prepareStatement = connection.prepareStatement(INSERT_QUERY);
			 prepareStatement.setInt(1,menu.getMenuId());
			 prepareStatement.setInt(2, menu.getRestuarantId());
			 prepareStatement.setString(3, menu.getItemName());
			 prepareStatement.setString(4,menu.getDescription());
			 prepareStatement.setDouble(5, menu.getPrice());
			 prepareStatement.setDouble(6, menu.getRating());
			 prepareStatement.setString(7, menu.isAvailable());
			 prepareStatement.setString(8, menu.getImagePath());
			 prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenuById(int menuId) {
		try {
			 prepareStatement = connection.prepareStatement(SELECT_QUERY);
			 prepareStatement.setInt(1, menuId);
			  res = prepareStatement.executeQuery();
			  while(res.next()) {
				  int menuId1=res.getInt("MenuID");
				  int resId= res.getInt("Idrestaurant");
				  String itemName=res.getString("ItemName");
				  String description =res.getString("Description");
				  Double price =res.getDouble("Price");
				  Double rating =res.getDouble("Ratings");
				  String isAvailable= res.getString("isAvailable");
				  String imagePath =res.getString("ImagePath");
				 Menu menu = new Menu(menuId1, resId, itemName, description, price, rating, isAvailable, imagePath);
				 return menu;
			  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateMenu(Menu menu) {
			try {
				 prepareStatement = connection.prepareStatement(UPDATE_QUERY);
				 prepareStatement.setInt(1,menu.getRestuarantId());
				 prepareStatement.setString(2, menu.getItemName());
				 prepareStatement.setString(3, menu.getDescription());
				 prepareStatement.setDouble(4, menu.getPrice());
				 prepareStatement.setDouble(5, menu.getRating());
				 prepareStatement.setString(6, menu.isAvailable());
				 prepareStatement.setInt(7, menu.getMenuId());
				  int executeUpdate = prepareStatement.executeUpdate();
				  
				  if(executeUpdate!= 0) {
					  return true;
				  }

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public boolean deleteMenu(int menuId) {
		try {
			 prepareStatement = connection.prepareStatement(DELETE_QUERY);
			 prepareStatement.setInt(1,menuId);
			    int executeUpdate = prepareStatement.executeUpdate();
			    if(executeUpdate != 0)
			    {
			    	return true;
			    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Menu> getAllMenusByRestaurant(int restaurantId) {
		ArrayList<Menu> menuList = new ArrayList<>();
		try {
			 prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			 prepareStatement.setInt(1, restaurantId);
			  res = prepareStatement.executeQuery();

			  while(res.next()) {
				  int menuId=res.getInt("MenuID");
				  int resId= res.getInt("Idrestaurant");
				  String itemName=res.getString("ItemName");
				  String description =res.getString("Description");
				  Double price =res.getDouble("Price");
				  Double rating =res.getDouble("Ratings");
				  String isAvailable= res.getString("isAvailable");
				  String imagePath =res.getString("ImagePath");
				  Menu menu = new Menu(menuId, resId, itemName, description,price,rating, isAvailable, imagePath);
				  menuList.add(menu);
			  }
		return menuList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
