<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Collection"%>
<%@ page
	import="com.fooddilivery.module.OrderHistory,com.fooddilivery.module.OrderItem"%>
<%@ page
	import="com.fooddilivery.module.User, com.fooddilivery.module.Menu"%>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
<link rel="stylesheet" href="orderHistory.css">
<link rel="icon"
	href="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png">

</head>
<body>

	<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    String username = (loggedInUser != null) ? loggedInUser.getUserName(): "Guest";

    List<OrderHistory> orderHistoryList = (List<OrderHistory>) request.getAttribute("orderList");
	%>
	<header>
		<div class="header-content">
			<h1>DevFooday</h1>
			<nav>
				<h2>Order History</h2>
				<% 
                if (username == null) { %>
				<a href="login1.jsp" class="nav-link">Login</a> <a
					href="signUp1.jsp" class="nav-link">Register</a>
				<% } else { %>
				<div class="dropdown">
					<button class="dropbtn">
						<i class="fas fa-user-circle"></i>
						<%=username%>
						<i class="fas fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<a href="home"><i class="fas fa-home"></i>Home</a> <a
							href="javascript:void(0)"
							onclick="document.getElementById('cartForm').submit();"> <i
							class="fas fa-shopping-cart"></i> View Cart
						</a> <a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i>
							Logout</a>
					</div>
				</div>
				<form id="cartForm" action="cart" method="post"
					style="display: none;">
					<input type="hidden" name="action" value="viewCart">
				</form>
				<% } %>
			</nav>
		</div>
	</header>
	<div class="container">
		<% if (orderHistoryList != null && !orderHistoryList.isEmpty()) { %>
		<% for (OrderHistory orderHistory : orderHistoryList) { %>
		<div class="order-card">
			<div class="order-info">
				<p>
					<strong>Order ID:</strong>
					<%= orderHistory.getOrderId() %></p>
				<p>
					<strong>Order Date:</strong>
					<%= orderHistory.getOrderDate() %></p>
				<p>
					<strong>Total Amount:</strong> &#x20B9;
					<%= orderHistory.getTotalAmount() %></p>
				<p>
					<strong>Status:</strong>
					<%= orderHistory.getStatus() %></p>
			</div>
			<div class="order-items">
				<h3>Items Ordered:</h3>
				<ul>
					<% List<OrderItem> itemOrderList = orderHistory.getOrderItems();
                               if (itemOrderList != null && !itemOrderList.isEmpty()) {
                                   for (OrderItem cartItem : itemOrderList) { 
                                       Menu menu = cartItem.getMenu();
                            %>
					<li><img alt="<%=menu.getItemName()%>"
						src="<%=menu.getImagePath()%>" />
						<p>
							<strong>Item Name:</strong>
							<%= menu.getItemName()%></p>
						<p>
							<strong>Price:</strong>&#x20B9;
							<%= menu.getPrice() %></p>
						<p>
							<strong>Quantity:</strong>
							<%= cartItem.getQuantity() %></p>
						<p>
							<strong>Total Price:</strong> &#x20B9;
							<%= cartItem.getTotalPrice() %></p></li>
					<% } } %>
				</ul>
			</div>
		</div>
		<% }
          } else { %>
		<p>No order history available.</p>
		<% } %>
	</div>

</body>
</html>
