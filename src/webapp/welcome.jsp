<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.fooddilivery.module.User"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DevFoodday - Your Favorite Food Delivery</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	line-height: 1.6;
}

header {
	background-color: #4CAF50;
	color: white;
	padding: 1rem;
}

.user-welcome {
	color: white;
	margin-right: 1rem;
}

.user-welcome i {
	margin-right: 0.5rem;
}

nav {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.logo {
	font-size: 1.5rem;
	font-weight: bold;
}

.nav-links a {
	color: white;
	text-decoration: none;
	padding: 0.5rem 1rem;
}

.nav-links a:hover {
	background-color: #45a049;
}

main {
	padding: 2rem;
	max-width: 800px;
	margin: 0 auto;
}

.hero {
	text-align: center;
	margin-bottom: 2rem;
}

.hero h1 {
	font-size: 2.5rem;
	color: #4CAF50;
}

.features {
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
}

.feature {
	flex-basis: 30%;
	text-align: center;
	margin-bottom: 1rem;
}

.contact {
	background-color: #f4f4f4;
	padding: 2rem;
	border-radius: 5px;
}

.contact h2 {
	color: #4CAF50; footer { background-color : #333;
	color: white;
	text-align: center;
	padding: 1rem;
	position: absolute;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body>
	<%
	User user = (User) session.getAttribute("loggedInUser");
	%>
	<header>
		<nav>
			<div class="logo">DevFoodday</div>
			<div class="nav-links">
				<%
				if (user == null) {
				%>
				<a href="login1.jsp">User Login</a> <a href="login1.jsp">Admin
					Login</a> <a href="signUp1.jsp">Register</a>
				<%
				} else {
				%>
				<span class="user-welcome"> <i class="fas fa-user-circle"></i>
					                    Welcome <%=user.getName()%>
				</span>
				 <a href="#about">About</a> <a href="#contact">Contact</a> <a
					href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a>
				<%
				}
				%>
			</div>
		</nav>
	</header>
	<main>
		<section class="hero">
			<h1>Welcome to DevFoodday</h1>
			<p>Your favorite meals, delivered fast and fresh</p>
		</section>

		<section class="about">
			<h2>About DevFoodday</h2>
			<p>DevFoodday is your go-to platform for ordering delicious meals
				from local restaurants. We connect food lovers with their favorite
				eateries, ensuring quick and reliable delivery right to your
				doorstep.</p>
		</section>

		<section class="features">
			<div class="feature">
				<h3>Wide Selection</h3>
				<p>Choose from hundreds of local restaurants</p>
			</div>
			<div class="feature">
				<h3>Fast Delivery</h3>
				<p>Get your food delivered in 30 minutes or less</p>
			</div>
			<div class="feature">
				<h3>Easy Ordering</h3>
				<p>User-friendly interface for seamless ordering</p>
			</div>
		</section>
		<section id="contact" class="contact">
			<h2>Contact Us</h2>
			<p>We're here to help! Reach out to us with any questions or
				concerns.</p>
			<ul>
				<li>Email: support@devfoodday.com</li>
				<li>Phone: (555) 123-4567</li>
				<li>Address: 123 Food Street, Cuisine City, FC 12345</li>
			</ul>
			<p>Business Hours: Monday to Friday, 9 AM - 6 PM</p>
		</section>
	</main>

	<footer>
		<p>&copy; 2024 DevFoodday. All rights reserved.</p>
	</footer>
</body>
</html>