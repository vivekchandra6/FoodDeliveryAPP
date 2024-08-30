<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.fooddilivery.module.OrderItem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordered Items</title>
<link rel="stylesheet" href="ordereditems.css">
</head>
<body>
	<header>
		<h1>DevFooday</h1>
		<nav>
			<a href="home">Home</a> 
			<a href="OrderHistory">Order History</a> <a
				href="LogoutServlet">Logout</a>
		</nav>
	</header>

	<div class="container">
		<h2>Your Ordered Items</h2>
		<div class="items-container">
			<%
                List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("itemorderList");
                if (orderItems != null && !orderItems.isEmpty()) {
                    double totalAmount = 0;
                    for (OrderItem item : orderItems) {
                        totalAmount += item.getTotalPrice();
            %>
			<div class="item-card">
				<div class="item-details">
					<h3><%= item.getMenu().getItemName()%></h3>
					<p>
						<strong>Quantity:</strong>
						<%= item.getQuantity() %></p>
					<p>
						<strong>Price per Item:</strong> &#x20B9;
						<%= item.getTotalPrice() / item.getQuantity() %></p>
					<p>
						<strong>Total Price:</strong> &#x20B9;
						<%= item.getTotalPrice() %></p>
				</div>
			</div>
			<%
                    }
            %>
			<div class="total-amount">
				<h3>
					Total Amount: &#x20B9;
					<%= totalAmount %></h3>
			</div>
			<%
                } else {
            %>
			<p>No items found for this order.</p>
			<%
                }
            %>
			<div class="actions">
				<a href="OrderHistory" class="btn">Back to Order History</a>
			</div>
		</div>
	</div>
</body>
</html>
