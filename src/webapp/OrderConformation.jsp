<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.fooddilivery.module.Order,com.fooddilivery.module.CartItem,java.util.Collection"%>
<%@ page import="com.fooddilivery.module.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation</title>
<link rel="stylesheet" href="orderConformation.css">
</head>
<body>

	<header>
		<div class="header-content">
			<h1>DevFooday</h1>
			<nav>
				<a href="home" class="nav-link">Home</a>
				<% 
                User username = (User) session.getAttribute("loggedInUser");
                if (username == null) { %>
				<a href="login1.jsp" class="nav-link">Login</a> <a
					href="signUp1.jsp" class="nav-link">Register</a>
				<% } else { %>
				<div class="user-links">
					<p><%= username.getUserName()%></p>
					<a href="OrderHistory" class="nav-link">Order History</a>
					<form action="cart" method="post" class="inline-form">
						<input type="hidden" name="action" value="viewCart"> <input
							type="submit" value="ViewCart" class="btn">
					</form>
					<a href="LogoutServlet" class="nav-link">Logout</a>
				</div>
				<% } %>
			</nav>
		</div>
	</header>

	<div class="order-confirmation">
		<h1>Order Confirmation</h1>

		<%
        String cartErrorMessage = (String) session.getAttribute("cartErrorMessage");
        if (cartErrorMessage != null) {
    %>
		<p class="error-message"><%= cartErrorMessage %></p>
		<%
        }
        session.removeAttribute("cartErrorMessage");

        String successMessage = (String) request.getAttribute("success");
        if (successMessage != null) { %>
		<h2 class="success-message"><%= successMessage %></h2>
		<%
        }
        request.removeAttribute("success");

        Order order = (Order) session.getAttribute("order");
        if (order != null) {
    %>
		<div class="order-details">
			<h2>Order Details:</h2>
			<%--  <p>User ID: <%= order.getUserId() %></p> --%>
			<p>
				Order ID:
				<%= order.getOrderId() %></p>
			<p>
				Order Date:
				<%= order.getOrderDate() %></p>
			<p>
				Total Amount: &#x20B9;
				<%= order.getTotalAmount() %></p>
			<p>
				Payment Mode:
				<%= order.getPayementMode()%></p>
			<p>
				Status:
				<%= order.getStatus() %></p>
		</div>
		<%
        }
    %>
		<div class="no-more-button">
			<form action="orderItems" method="post">
				<input type="hidden" name="action" value="KnowMore">
				<button type="submit" class="btn" id="knowMoreBtn">Know
					More</button>
			</form>
		</div>
	</div>

	<script>
    function disableButton() {
        document.getElementById('knowMoreBtn').disabled = true;
        document.getElementById('knowMoreBtn').innerText = 'Processing...';
    }
</script>

</body>
</html>
