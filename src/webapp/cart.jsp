<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Map, com.fooddilivery.module.Cart, com.fooddilivery.module.CartItem, com.fooddilivery.module.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet" href="cart.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon"
	href="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png">

</head>
<body>
	<header>
		<div class="header-content">
			<h1 class="site-title">DevFooday</h1>
			<nav>
				<a href="home">Home</a>
				<% 
                User username = (User)session.getAttribute("loggedInUser");
                if (username != null) { %>
				<div class="dropdown">
					<button class="dropbtn">
						<i class="fas fa-user-circle"> </i><%= username.getName() %>
						<i class="fas fa-caret-down"> </i>
					</button>
					<div class="dropdown-content">
						<!--    <a href="javascript:void(0)" onclick="document.getElementById('cartForm').submit();">
                                <i class="fas fa-shopping-cart"> </i> View Cart
                            </a> -->
						<a href="OrderHistory"><i class="fas fa-box"> </i> My Orders</a> <a
							href="profile.jsp"><i class="fas fa-user-edit"> </i> Profile
							Update</a> <a href="LogoutServlet"><i class="fas fa-sign-out-alt">
						</i> Logout</a>
					</div>
				</div>
				<!-- <form id="cartForm" action="cart" method="post">
                        <input type="hidden" name="action" value="viewCart">
                    </form> -->
				<% } else { %>
				<a href="login1.jsp">Login</a> <a href="signUp1.jsp">Register</a>
				<% } %>
			</nav>
		</div>
	</header>

	<h1>Your Cart</h1>
	<div class="cart-items">
		<% 
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            double totalAmount = 0;
            for (CartItem item : cart.getItems().values()) {
                double itemTotal = item.getQuantity() * item.getPrice();
                totalAmount += itemTotal;
                session.setAttribute("totalAmount", totalAmount);
        %>
		<div class="cart-item">
			<h3><%= item.getName() %></h3>
			<p>
				₹
				<%= item.getPrice() %></p>
			<p>
				Quantity:
				<%= item.getQuantity() %></p>
			<p>
				Total: ₹
				<%= itemTotal %></p>
			<form action="cart" method="post">
				<input type="hidden" name="itemId" value="<%= item.getItemId()%>">
				Quantity: <input type="number" name="quantity"
					value="<%= item.getQuantity() %>" min="1"> <input
					type="submit" name="action" value="Update" class="update-btn">
				<input type="submit" name="action" value="Remove" class="remove-btn">
			</form>
		</div>
		<% } %>
		<div class="cart-total">
			<h2>
				Total Amount: ₹
				<%= totalAmount %></h2>
		</div>
		<a
			href="MenuServlete?restaurantId=<%= session.getAttribute("restaurantId") %>"
			class="btn add-more-items-btn">Add More Items</a>

		<form action="checkout.jsp" method="post">
			<% if (username == null) {
                session.setAttribute("userErrorMessage", "You need to login to complete your order.");
            %>
			<a href="errorPage.jsp" class="btn proceed-to-checkout-btn">Proceed
				to Checkout</a>
			<% } else { %>
			<input type="submit" value="ProceedtoCheckout"
				class="btn proceed-to-checkout-btn">
			<% } %>
		</form>
		<% } else { %>
		<p>Your cart is empty.</p>
		<% } %>
	</div>
</body>
</html>
