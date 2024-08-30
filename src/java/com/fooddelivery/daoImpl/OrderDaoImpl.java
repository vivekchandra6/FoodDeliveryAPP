package com.fooddelivery.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.util.DBConnectionUtil;
import com.food.util.OrderIdGenerator;
import com.fooddilivery.module.Order;
import com.fooddivery.dao.OrderDao;

public class OrderDaoImpl implements OrderDao{

	private static Connection connection =null;
	private static PreparedStatement prepareStatement =null;
	private ResultSet res =null;
	Statement statement = null;

	private final static String INSERT_QUERY = "insert into orderdata(`OrderID`,`UserID`, `Idrestaurant`,`TotalAmount`, `Status`, `PaymentMode`)values(?,?,?,?,?,?)";
	private final static String SELECT_QUERY ="select * from `orderdata`  where `OrderID`=?";
	private final static String DELETE_QUERY ="delete from `orderdata` where `OrderID`=? ";
	private final static String UPDATE_QUERY ="update `orderdata` set `UserID`=?, `Idrestaurant`=?, `OrderDate`=?,`TotalAmount`=?, `Status`=?,`PaymentMode`=? where `OrderID`=? ";
	private final static String SELECT_ALL_QUERY ="select * from `orderdata`";

	public OrderDaoImpl() {

		/*
		 * try { Class.forName("com.mysql.cj.jdbc.Driver"); connection =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp","root",
		 * "Sanju@71");
		 * 
		 * } catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		 */
		connection = DBConnectionUtil.getConnection();

	}



	@Override
	public void addOrder(Order order) {


		try {
	        String generatedOrderId =  OrderIdGenerator.generateOrderId();
	        order.setOrderId(generatedOrderId);

            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setString(1, order.getOrderId());
            prepareStatement.setInt(2, order.getUserId());
            prepareStatement.setInt(3, order.getRestuarantId());
//            prepareStatement.setDate(4, new java.sql.Date(order.getOrderDate().getTime()));
            prepareStatement.setDouble(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPayementMode());

            int executeUpdate = prepareStatement.executeUpdate();
              if(executeUpdate > 0) {
//            	  System.out.println("orderPlaces "+ executeUpdate);
               }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Order getOrder(int orderId) {

		try {
			 prepareStatement = connection.prepareStatement(SELECT_QUERY);
			 prepareStatement.setInt(1, orderId);
			  res = prepareStatement.executeQuery();
			  while(res.next()) {
				  String orderId1=res.getString("OrderID");
				  int userId= res.getInt("UserID");
				  int restId=res.getInt("Idrestaurant");
				  Date orderDate=res.getDate("OrderDate");
				  Double totalAmount =res.getDouble("TotalAmount");
				  String status=res.getString("Status");
				  String payment= res.getString("PaymentMode");
				  return new Order(orderId1, userId, restId, orderDate, totalAmount, status, payment);
			  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateOrder(Order order) {

			try {
				 prepareStatement = connection.prepareStatement(UPDATE_QUERY);
				 prepareStatement.setInt(1,order.getUserId());
				 prepareStatement.setInt(2, order.getRestuarantId());
				 prepareStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
				 prepareStatement.setDouble(4, order.getTotalAmount());
				 prepareStatement.setString(5, order.getStatus());
				 prepareStatement.setString(6, order.getPayementMode());
				   prepareStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}





	}

	@Override
	public void deleteOrder(int orderId) {
		try {
			 prepareStatement = connection.prepareStatement(DELETE_QUERY);
			 prepareStatement.setInt(1,orderId);
			 int result = prepareStatement.executeUpdate();
			 
			 System.out.println("Order deleted "+ result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Order> getAllOrderByUser(int userId) {

		ArrayList<Order> orderList = new ArrayList<>();
		try {
			statement = connection.createStatement();
			  res = statement.executeQuery(SELECT_ALL_QUERY);
			  while(res.next()) {
				  String orderId=res.getString("orderId1");
				  int userId1= res.getInt("UserID");
				  int restId=res.getInt("Idrestaurant");
				  Date orderDate=res.getDate("OrderDate");
				  Double totalAmount =res.getDouble("TotalAmount");
				  String status=res.getString("Status");
				  String payment= res.getString("PaymentMode");
				  Order order = new Order(orderId, userId1, restId, orderDate, totalAmount, status, payment);
				  orderList.add(order);
			  }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

}
