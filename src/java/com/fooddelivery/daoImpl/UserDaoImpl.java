package com.fooddelivery.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.food.util.DBConnectionUtil;
import com.fooddilivery.module.User;
import com.fooddivery.dao.UserDao;

public class UserDaoImpl implements UserDao {


	private static Connection connection =null;
	private static PreparedStatement prepareStatement =null;
	private ResultSet res =null;
	private Statement statement =null;
	private final static String INSERT_QUERY = "insert into `user`(`Name`, `Username`, `Password`, `Email`, "
			+ "`PhoneNumber`, `Adress`, `Role`)values(?,?,?,?,?,?,?)";
	private final static String SELECT_QUERY ="select * from `user`  where `UserID`=?";
	private final static String DELETE_QUERY ="delete from `user` where `UserID`=? ";
	private final static String UPDATE_QUERY ="update `user` set `Name`=?, `Username`=?,`Email`=?, `PhoneNumber`=?,`Adress`=? where `UserID`=? ";
	private final static String SELECT_ALL_QUERY ="select * from `user`";
	private final static String SELECT_By_EMAIL = "select * from user where email = ?";
	private final static String RESET_TOKEN = "update user set `reset_token` = ?, `reset_token_expiry`= ? where email = ?";
	private final static String UPDATE_PASSWORD = "update user set password = ? where reset_token = ?";
	private final static String DELETE_TOKEN = "update user set reset_token = null where `email` = ?";
	private static final String HOST = "smtp.gmail.com";
	private static final String PORT = "587";
	//	 private static final String FROM_EMAIL = "noreply@yourfoodapp.com";
	private static final String USERNAME = System.getenv("EMAIL_USERNAME"); // Get from environment variable
	private static final String PASSWORD = System.getenv("EMAIL_PASSWORD"); // Get from environment variable


	public UserDaoImpl() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp","root","Sanju@71");
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
		connection = DBConnectionUtil.getConnection();
	}
	@Override
	public void addUser(User user) {
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUserName());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setLong(5, user.getPhoneNum());
			prepareStatement.setString(6, user.getAdress());
			prepareStatement.setString(7, user.getRole());

			 prepareStatement.executeUpdate();
//			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {

		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);
			prepareStatement.setInt(1, userId);
			res = prepareStatement.executeQuery();
			while(res.next()) {
				int userId1=res.getInt("UserID");
				String name=res.getString("Name");
				String userName=res.getString("Username");
				String password=res.getString("Password");
				String email=res.getString("Email");
				long phoneNum =res.getLong("PhoneNumber");
				String adress=res.getString("Adress");
				String role=res.getString("Role");

				return new User(userId1, name, userName, password, email, phoneNum, adress, role);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public void updateUser(User user) {

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUserName());
//			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(3, user.getEmail());
			prepareStatement.setLong(4, user.getPhoneNum());
			prepareStatement.setString(5, user.getAdress());
//			prepareStatement.setString(7, user.getRole());
			prepareStatement.setInt(6, user.getUserID());
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, userId);
	       prepareStatement.executeUpdate();

//              System.out.println("User Deleted: "+i);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getallUser() {
		ArrayList<User> userList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			res = statement.executeQuery(SELECT_ALL_QUERY);
			while(res.next()) {
				int userId=  res.getInt("UserID");
				String name=res.getString("Name");
				String userName=res.getString("Username");
				String password=res.getString("Password");
				String email=res.getString("Email");
				long phoneNum =res.getLong("PhoneNumber");
				String adress=res.getString("Adress");
				String role=res.getString("Role");


				User user = new User(userId, name, userName, password, email, phoneNum, adress, role);
				userList.add(user);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}


		return userList;
	}
	@Override
	public User getUser(String username) {
		try {
			prepareStatement = connection.prepareStatement("SELECT * FROM `user` WHERE `Username` = ?");
			prepareStatement.setString(1, username);
			res = prepareStatement.executeQuery();

			if (res.next()) {
				int userId = res.getInt("UserID");
				String name = res.getString("Name");
				String password = res.getString("Password");
				String email = res.getString("Email");
				long phoneNum = res.getLong("PhoneNumber");
				String address = res.getString("Adress");
				String role = res.getString("Role");

				return new User(userId, name, username, password, email, phoneNum, address, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updatePassword(String updatedPassword, String token) {
		try {
			prepareStatement = connection.prepareStatement(UPDATE_PASSWORD);

			prepareStatement.setString(1, updatedPassword);
			prepareStatement.setString(2, token);

			 prepareStatement.executeUpdate();

//			System.out.println("Password Updated: "+ executeUpdate);
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;


	}

	public User getUserByEmail(String email) {
		try {
			prepareStatement = connection.prepareStatement(SELECT_By_EMAIL);

			prepareStatement.setString(1, email);

			res = prepareStatement.executeQuery();

		 if(res.next()) {
			 	int userId = res.getInt("UserID");
				String name = res.getString("Name");
				 String username = res.getString("Username");
				String password = res.getString("Password");
				long phoneNum = res.getLong("PhoneNumber");
				String address = res.getString("Adress");
				String role = res.getString("Role");
				return new User(userId, name, username, password, email, phoneNum, address, role);
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean getByEmail(String email) {
		try {
			prepareStatement = connection.prepareStatement(SELECT_By_EMAIL);

			prepareStatement.setString(1, email);

			res = prepareStatement.executeQuery();
		return res.next();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String generateAndStoreResetToken(String email) {

		String resetToken = UUID.randomUUID().toString();

		try {
			prepareStatement = connection.prepareStatement(RESET_TOKEN);

			LocalDateTime expiryTime =   LocalDateTime.now().plusHours(1);

			prepareStatement.setString(1, resetToken);
			prepareStatement.setTimestamp(2, Timestamp.valueOf(expiryTime));
			prepareStatement.setString(3, email);

			int executeUpdate = prepareStatement.executeUpdate();

			if(executeUpdate> 0) {

				return resetToken;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
   
	public void deleteToken(String email) {
		 try {
			prepareStatement = connection.prepareStatement(DELETE_TOKEN);
			prepareStatement.setString(1, email);
            int executeUpdate = prepareStatement.executeUpdate();
            
            if (executeUpdate > 0) {
                System.out.println("Reset token removed successfully for email: " + email);
            } else {
                System.out.println("No user found with email: " + email);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public  void sendResetEmail(String toEmail, String resetToken) throws AddressException, MessagingException {
		// Check if the provided email is in a valid format
		if (!toEmail.matches("(?i)^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			throw new IllegalArgumentException("Invalid email address: " + toEmail);
		}
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.port", PORT);
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Explicitly enable TLSv1.2

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(USERNAME));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		message.setSubject("Password Reset for Your Food App");
		//http://localhost:5221/resetPassword?token=733207cd-4fe9-4d89-a0ab-dfd9ad4634fb
		String resetLink =  "http://localhost:5221/Food_Delivery_app/resetPassword?token="+resetToken;
		String emailContent = "Dear User,\n\n"
				+ "You have requested to reset your password for Your Food App. "
				+ "Please click on the link below to reset your password:\n\n"
				+resetLink+"\n\n"
				+ "This link will expire in 1 hour for security reasons.\n\n"
				+ "If you did not request a password reset, please ignore this email.\n\n"
				+ "Best regards,\n"
				+ "Your Food App Team";

		message.setText(emailContent);
		Transport.send(message);
	}
	
}
