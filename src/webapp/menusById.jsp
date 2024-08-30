<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.fooddilivery.module.Menu, java.util.List, com.fooddilivery.module.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menus for Restaurant</title>
<link rel="stylesheet" href="menuById.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<% 
        User admin = (User)session.getAttribute("admin");
        String adminName = admin != null ? admin.getName() : "Guest";
    %>
	<header>
		<div class="header-content">
			<h1 class="site-title">DevFooday</h1>
			<nav>
				<a href="AdminDashBoard" class="nav-link">View Restaurants</a>
				<!--                 <a href="ViewRestaurants" class="nav-link">View Restaurants</a>
 -->
				<div class="dropdown">
					<button class="dropbtn">
						<i class="fas fa-user-circle"></i>
						<%= adminName %>
						<i class="fas fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<a href="profile.jsp"><i class="fas fa-user-edit"></i> Profile</a>
						<a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i>
							Logout</a>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<%
    %>

	<div class="container">
		<!-- Display success or error message -->
		<%
            String successMessage = (String) request.getAttribute("success");
            String errorMessage = (String) request.getAttribute("error");
            if (successMessage != null) {
        %>
		<div class="message success"><%= successMessage %></div>
		<% 
            } else if (errorMessage != null) { 
        %>
		<div class="message error"><%= errorMessage %></div>
		<% 
            } 
        %>

		<h2>Menus for Restaurant</h2>
		<% 
            List<Menu> menus = (List<Menu>) request.getAttribute("menusByResId");
            if (menus != null && !menus.isEmpty()) {
        %>
		<ul class="menu-list">
			<% for (Menu menu : menus) { %>
			<li class="menu-item"><img alt="<%=menu.getItemName()%>"
				src="<%=menu.getImagePath()%>" class="menu-image">
				<div class="menu-details">
					<h3>
						<i class="fas fa-utensils"></i>
						<%= menu.getItemName() %></h3>
					<p>
						<i class="fas fa-align-left"></i> <strong>Description:</strong>
						<%= menu.getDescription() %></p>
					<p>
						<strong>Price:</strong><i class="fas fa-rupee-sign"></i>
						<%= menu.getPrice() %></p>
					<p>
						<i class="fas fa-star"></i> <strong>Rating:</strong>
						<%= menu.getRating() %></p>
					<p>
						<i class="fas fa-check-circle"></i> <strong>Status:</strong>
						<%= menu.isAvailable() %></p>
					<form action="EditServlet" method="post" class="menu-actions">
						<input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
						<button type="submit" class="btn edit-btn">Edit</button>
					</form>
					<form action="DeleteServlet" method="post" class="menu-actions"
						onsubmit="return confirm('Are you sure you want to delete this menu item?');">
						<input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
						<button type="submit" class="btn delete-btn">Delete</button>
					</form>

				</div></li>
			<% } %>
		</ul>
		<% } else { %>
		<p>No menus found for this restaurant.</p>
		<% } %>
	</div>

	<!--     <a href="AdminDashboard.jsp" class="btn back-btn">Back to Dashboard</a>
 -->
</body>
</html>
