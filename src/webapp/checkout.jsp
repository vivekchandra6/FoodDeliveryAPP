<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fooddilivery.module.User"%>
<%@ page import="java.text.DecimalFormat"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout</title>
<link rel="stylesheet" href="checkout.css">
<link rel="icon"
	href="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<header>
		<div class="header-content">
			<h1>DevFooday</h1>
			<% 
            User user = (User)session.getAttribute("loggedInUser");
            if (user != null) { %>
			<div class="user-links">
				<p><%= user.getUserName() %></p>
				<a href="LogoutServlet">Logout</a>
			</div>
			<% } %>
		</div>
	</header>
	<main class="checkout-container">
		<h2>Checkout</h2>
		<!-- Display the Total Amount -->
		<div class="total-amount">
			<% 
            Double totalAmount = (Double) session.getAttribute("totalAmount");
            DecimalFormat df = new DecimalFormat("0.00");
            if (totalAmount != null) { %>
			<h3>
				Total Amount to be Paid: Rs.<%= df.format(totalAmount) %></h3>
			<% } else { %>
			<h3>Total Amount: RS.0.00</h3>
			<% } %>
		</div>
		<form action="CheckOutServlet" method="post" class="checkout-form"
			id="checkoutForm">
			<div class="form-group">
				<label for="deliveryAddress"><i class="fa fa-address-card"></i>
					Delivery Address:</label>
				<textarea id="deliveryAddress" name="deliveryAddress" required></textarea>
			</div>
			<div class="form-group">
				<label for="paymentMode"><i class="fa fa-credit-card"></i>
					Payment Mode:</label> <select id="paymentMode" name="paymentMode" required
					onchange="showPaymentDetails()">
					<option value="">Select Payment Mode</option>
					<option value="UPI">UPI</option>
					<option value="Cash">Cash</option>
					<option value="Card">Card</option>
					<!--                     <option value="PayPal">PayPal</option>
 -->
				</select>
			</div>
			<div id="upiDetails" class="payment-details" style="display: none;">
				<div class="form-group">
					<label for="upiId"><i class="fa fa-id-card"></i> UPI ID:</label> <input
						type="text" id="upiId" name="upiId"
						placeholder="Enter your UPI ID">
				</div>
			</div>
			<div id="cardDetails" class="payment-details" style="display: none;">
				<div class="form-group">
					<label for="cardNumber"><i class="fa fa-credit-card"></i>
						Card Number:</label> <input type="text" id="cardNumber" name="cardNumber"
						placeholder="Enter your card number">
				</div>
				<div class="form-group">
					<label for="cardName"><i class="fa fa-user"></i> Name on
						Card:</label> <input type="text" id="cardName" name="cardName"
						placeholder="Enter name on card">
				</div>
				<div class="form-group">
					<label for="expiryDate"><i class="fa fa-calendar"></i>
						Expiry Date:</label> <input type="text" id="expiryDate" name="expiryDate"
						placeholder="MM/YY">
				</div>
				<div class="form-group">
					<label for="cvv"><i class="fa fa-lock"></i> CVV:</label> <input
						type="text" id="cvv" name="cvv" placeholder="Enter CVV">
				</div>
			</div>
			<button type="submit" id="PO" class="btn">Place Order</button>
		</form>
	</main>

	<script>
        function showPaymentDetails() {
            var paymentMode = document.getElementById("paymentMode").value;
            var upiDetails = document.getElementById("upiDetails");
            var cardDetails = document.getElementById("cardDetails");

            upiDetails.style.display = "none";
            cardDetails.style.display = "none";
            
            // Get the card details fields
            var cardNumber = document.getElementById("cardNumber");
            var cardName = document.getElementById("cardName");
            var expiryDate = document.getElementById("expiryDate");
            var cvv = document.getElementById("cvv");

            if (paymentMode === "UPI") {
                upiDetails.style.display = "block";
            } else if (paymentMode === "Card") {
                cardDetails.style.display = "block";
                
                // Add the required attribute back if "Card" is selected
                cardNumber.setAttribute("required", "required");
                cardName.setAttribute("required", "required");
                expiryDate.setAttribute("required", "required");
                cvv.setAttribute("required", "required");

            }
        }
    </script>

</body>
</html>