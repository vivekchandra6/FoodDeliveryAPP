package com.fooddelivery.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.util.DBConnectionUtil;
import com.fooddilivery.module.Order;
import com.fooddilivery.module.OrderHistory;
import com.fooddivery.dao.OrderHistoryDao;

public class OrderHistoryDaoImpl implements OrderHistoryDao {

	private  Connection connection;
	private  PreparedStatement prepareStatement =null;
	private ResultSet res =null;

	private final static String INSERT_QUERY = "insert into `orderhistory`( `UserID`, `OrderID`, `TotalAmount`)values(?,?,?)";
	private final static String SELECT_QUERY ="select * from `orderhistory`  where `OrderHistoryID`=?";
	private final static String DELETE_QUERY ="delete from `orderhistory` where `OrderHistoryID`=? ";
	private final static String UPDATE_QUERY ="update `orderhistory` set `UserID`=?, `OrderID`=?, `OrderDate`=?,`TotalAmount`=?,Status`=? where `OrderHistoryID`=? ";
	private final static String SELECT_BY_ID ="select * from `orderhistory` where UserId = ? order by orderdate desc";



	public OrderHistoryDaoImpl() {
		/*
		 * try { Class.forName("com.mysql.cj.jdbc.Driver"); connection =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp","root",
		 * "Sanju@71"); } catch (ClassNotFoundException | SQLException e) {
		 * e.printStackTrace();
		} */
		this.connection = DBConnectionUtil.getConnection();
              
	}

	@Override
	public void addOrderHistory(Order order) {
		try {
			 prepareStatement = connection.prepareStatement(INSERT_QUERY);
			 prepareStatement.setInt(1,order.getUserId());
			 prepareStatement.setString(2, order.getOrderId());
//			 prepareStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
			 prepareStatement.setDouble(3,order.getTotalAmount());
//			 prepareStatement.setString(5,"Delivered");
			   int executeUpdate = prepareStatement.executeUpdate();
			   if(executeUpdate > 0) {
//		            System.out.println("Order added to history successfully with default status.");
			   }

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		try {
			 prepareStatement = connection.prepareStatement(SELECT_QUERY);
			 prepareStatement.setInt(1, orderHistoryId);
			  res = prepareStatement.executeQuery();
			  while(res.next()) {
				  int orderHistoryId1=res.getInt("OrderHistoryID");
				  int userId= res.getInt("UserID");
				  String orderId=res.getString("OrderID");
				  Date orderDate =res.getDate("OrderDate");
				  Double totalAmount =res.getDouble("TotalAmount");
				  String status =res.getString("Status");
				  return new OrderHistory(orderHistoryId1, userId, orderId, orderDate, totalAmount, status);
			  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		
			try {
				 prepareStatement = connection.prepareStatement(UPDATE_QUERY);
				 prepareStatement.setInt(1,orderHistory.getUserId());
				 prepareStatement.setString(2, orderHistory.getOrderId());
				 prepareStatement.setDate(3, new java.sql.Date(orderHistory.getOrderDate().getTime()));
				 prepareStatement.setDouble(4, orderHistory.getTotalAmount());
				 prepareStatement.setString(5, orderHistory.getStatus());
				 prepareStatement.setInt(5, orderHistory.getOrderHistoryId());
				 prepareStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		try {
			 prepareStatement = connection.prepareStatement(DELETE_QUERY);
			 prepareStatement.setInt(1,orderHistoryId);
			   prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderHistory> getOrderHistoriesByUserId(int userId) {
		List<OrderHistory> orderHistoryList = new ArrayList<>();
		try {
			prepareStatement = connection.prepareStatement(SELECT_BY_ID);
			 prepareStatement.setInt(1, userId);
			  res = prepareStatement.executeQuery();
			  while(res.next()) {
				  int orderHistoryId=res.getInt("OrderHistoryID");
				  int userId1= res.getInt("UserID");
				  String orderId=res.getString("OrderID");
				  Date orderDate =res.getDate("OrderDate");
				  Double totalAmount =res.getDouble("TotalAmount");
				  String status =res.getString("Status");
				  OrderHistory orderHistory = new OrderHistory(orderHistoryId, userId1, orderId, orderDate, totalAmount, status);

				  
				  orderHistoryList.add(orderHistory);
			  }
				return orderHistoryList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
