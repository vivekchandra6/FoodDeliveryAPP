<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.fooddilivery.module.Menu, com.fooddilivery.module.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Menu - DevFooday</title>
<link rel="stylesheet" href="menu1.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon"
	href="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png">

</head>
<body>
	<header>
		<div class="header-content">
			<h1>DevFooday</h1>
			<nav>
				<%
                User user = (User)session.getAttribute("loggedInUser");
                if (user == null) { %>
				<a href="home">Home</a> <a href="login1.jsp">Login</a> <a
					href="signUp1.jsp">Register</a>
				<% } else { %>
				<a href="home">Restaurants</a>
				<div class="user-links">
					<div class="dropdown">
						<button class="dropbtn">
							<i class="fas fa-user-circle"></i>
							<%= user.getName()%>
							<i class="fas fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<a href="javascript:void(0)"
								onclick="document.getElementById('cartForm').submit();"><i
								class="fas fa-shopping-cart"></i> View Cart</a> <a
								href="OrderHistory"><i class="fas fa-box"></i> My Orders</a> <a
								href="profile.jsp"><i class="fas fa-user-edit"></i> Profile
								Update</a> <a href="LogoutServlet"><i
								class="fas fa-sign-out-alt"></i> Logout</a>
						</div>
					</div>
				</div>
				<form id="cartForm" action="cart" method="post">
					<input type="hidden" name="action" value="viewCart">
				</form>
				<% } %>
			</nav>
		</div>
	</header>

	<main>
		<h2 class="heading">Our Menu</h2>

		<div class="container">
			<% 
            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
            if (menuList != null) {
                for(Menu menu : menuList) { 
            %>
			<div class="menu-card">
				<img alt="<%= menu.getItemName() %>"
					src="<%= menu.getImagePath() %>">
				<div class="menu-info">
					<h3>
						<i class="fas fa-utensils"></i>
						<%= menu.getItemName() %></h3>
					<p>
						<i class="fas fa-align-left"></i>
						<%= menu.getDescription() %></p>
					<p>
						<i class="fas fa-rupee-sign"></i> Price: &#x20B9;<%= menu.getPrice() %></p>
					<p>
						<i class="fas fa-star"></i> Rating:
						<%= menu.getRating()%></p>
					<p>
						<i class="fas fa-check-circle"></i> Availability:
						<c:choose>
							<c:when test="${isAvailable}">
								<span class="available">Close</span>
							</c:when>
							<c:otherwise>
								<span class="not-available">Open</span>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
				<form action="cart" method="post" class="menu-form">
					<label for="quantity"><i class="fas fa-sort-numeric-up"></i>
						Quantity:</label> <input type="number" id="quantity" name="quantity"
						value="1" min="1"> <input type="submit" name="action"
						value="AddtoCart" class="add-to-cart-btn"> <input
						type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
				</form>
			</div>
			<% 
                } 
            } else { 
            %>
			<p class="no-menu-items">No menu items available.</p>
			<% 
            } 
            %>
		</div>
	</main>
</body>
</html>
