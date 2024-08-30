package com.fooddivery.dao;

import java.util.List;

import com.fooddilivery.module.User;

public interface UserDao {
	void addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getallUser();
	public User getUser(String username);


}
