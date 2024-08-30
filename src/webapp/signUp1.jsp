<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<link rel="stylesheet" href="singup.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
</head>
<body>
	<div class="wrapper">
		<form action="SignUpServlet" method="Post">
			<h1>SignUp</h1>

			<div class="input-box">
				<i class='bx bx-user-circle'></i> <input type="text" name="name"
					placeholder="Name" required>
			</div>
			<div class="input-box">
				<input type="text" name="username" placeholder="User Name" required>
				<i class='bx bx-user-circle'></i>
			</div>

			<div class="input-box">
				<input type="password" name="password" placeholder="Password"
					required> <i class='bx bxs-lock-alt'></i>
			</div>
			<div class="input-box">
				<input type="text" name="email" placeholder="Email" required>
				<i class='bx bx-envelope'></i>
			</div>
			<div class="input-box">
				<input type="text" pattern="\d{10}" name="number"
					placeholder="please enter exactly 10 numbers" required> <i
					class='bx bx-phone'></i>
			</div>
			<div class="input-box">
				<input type="text" name="adress" placeholder="address" required>
				<i class='bx bx-map'></i>
			</div>
			<div class="input-box">
				<select name="role" id="role">
					<option value="Customer">Customer</option>
					<option value="Restuarant">Restaurant</option>
					<option value="DilivaryBay">DeliveryBay</option>
				</select> <i class='bx bx-envelope'></i>
			</div>


			<div class="submit-box">
				<input type="submit" value="SignUp">
			</div>
			<div class="signup-link">
				<p>
					Already have an account? <a href="login1.jsp">Login</a>
			</div>


		</form>

	</div>



</body>
</html>