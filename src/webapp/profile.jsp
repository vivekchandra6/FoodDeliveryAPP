<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fooddilivery.module.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link rel="stylesheet" href="profile.css">
<link rel="icon"
	href="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png">

</head>
<body>
	<header>
		<h1>MyProfile</h1>
		<nav>
	<% 
    User user = (User) session.getAttribute("loggedInUser");
    User admin = (User) session.getAttribute("admin");
    if (user == null &&  admin == null) {
        response.sendRedirect("login1.jsp");
    }  
    User currentUser = (user != null) ? user : admin;
%>
		
			<a href="<%= admin != null ? "AdminDashBoard" : "home" %>">Home</a>
			<a href="LogoutServlet">Logout</a>
		</nav>
	</header>
	<div class="profile-container">
		<% 
        String updatedMessage = (String) request.getAttribute("updated");
        if (updatedMessage != null) {
    %>
		<div class="success-message">
			<%= updatedMessage %>
		</div>
		<% } %>

		<form action="ProfileServlet" method="post">
			<input type="hidden" id="id" name="Id"
				value="<%= currentUser.getUserID() %>" /> <label for="name">Name:</label>
			<input type="text" id="name" name="name"
				value="<%= currentUser.getName() %>" required> <label
				for="username">Username:</label> <input type="text" id="username"
				name="Username" value="<%= currentUser.getUserName() %>" required>

			<label for="email">Email:</label> <input type="email" id="email"
				name="email" value="<%= currentUser.getEmail() %>" required> <label
				for="phone">Phone:</label> <input type="text" id="phone"
				name="phone" value="<%= currentUser.getPhoneNum() %>" required> <label
				for="address">Address:</label>
			<textarea id="address" name="address" required><%= currentUser.getAdress() %></textarea>

			<input type="submit" value="Update Profile">
		</form>
	</div>
</body>
</html>
