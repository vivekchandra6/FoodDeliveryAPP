<%@ page
	import="java.util.List, com.fooddilivery.module.Restaurant, com.fooddilivery.module.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage=""%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DevFooday - Your Favorite Food Delivery</title>
<link rel="stylesheet" href="home.css">
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
				<a href="welcome.jsp">Home</a> <a href="#restaurants">Restaurants</a> 
<!-- 				<a href="#about">About</a>
 -->				<%
                User user = (User)session.getAttribute("loggedInUser");
                
                if (user == null) {
                %>
				<a href="login1.jsp">Login</a> <a href="signUp1.jsp">Register</a>
				<% } else { %>
				<div class="user-links">
					<div class="dropdown">
						<button class="dropbtn">
							<i class="fas fa-user-circle"></i> Welcome<%=user.getName()%>
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
		<section id="home" class="hero">
			<div class="hero-content">
				<h2>Delicious food, delivered to your doorstep</h2>
				<p>Explore local flavors with DevFooday</p>
				<a href="#restaurants" class="cta-button">Order Now</a>
			</div>
		</section>

		<section id="restaurants">
			<h2>Popular Restaurants</h2>
			<div class="container">
				<%
                List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurantList");
                if (restaurants != null && !restaurants.isEmpty()) {
                    for(Restaurant res : restaurants) {
                %>
				<a href="MenuServlete?restaurantId=<%=res.getResturantId()%>"
					class="restaurant"> <img alt="<%=res.getName()%>"src="<%=res.getImagePath()%>">
  					<div class="restaurant-info">
						<h3><%= res.getName()%></h3>
						<p>
							<i class="fas fa-star"></i>
							<%=res.getRating()%>
							| <i class="fas fa-clock"></i>
							<%= res.getETA()%></p>
						<p>
							<i class="fas fa-utensils"></i>
							<%=res.getCousineType()%></p>
					</div>
				</a>
				<%
                    }
                } else {
                %>
				<div class="no-restaurants">
					<p>No restaurants available. Please check back later.</p>
				</div>
				<% } %>
			</div>
		</section>

		<section id="about">
			<h2>About DevFooday</h2>
			<div class="about-content">
				<div class="about-text">
					<p>DevFooday is a cutting-edge food delivery platform designed
						to connect hungry customers with their favorite local restaurants.
						Our mission is to make food ordering and delivery as seamless and
						enjoyable as possible.</p>
					<h3>Key Features:</h3>
					<ul>
						<li><i class="fas fa-store"></i> Wide selection of local
							restaurants</li>
						<li><i class="fas fa-mobile-alt"></i> Easy-to-use interface
							for browsing and ordering</li>
						<li><i class="fas fa-lock"></i> Secure payment processing</li>
						<li><i class="fas fa-star"></i> Customer reviews and ratings</li>
					</ul>
				</div>
				<div class="about-image">
					<img
						src="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png"
						alt="DevFooday App" />
				</div>
			</div>
		</section>
	</main>

	<footer>
		<p>&copy; 2024 DevFooday. All rights reserved.</p>
	</footer>
</body>
</html>
