package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

	  	private static final String Database_url = "jdbc:mysql://localhost:3306/foodapp";
	    private static final String Database_User = "root";
	    private static final String Database_password = "Sanju@71";

	    private static Connection con = null;

	    private DBConnectionUtil() {
	    }

	    public static Connection getConnection() {
	        if (con == null) {

	            synchronized (DBConnectionUtil.class) {
	                if (con == null) {
	                    try {
	                        Class.forName("com.mysql.cj.jdbc.Driver");
	                        con = DriverManager.getConnection(Database_url, Database_User, Database_password);
	                    } catch (ClassNotFoundException | SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	        return con;
	    }

}
