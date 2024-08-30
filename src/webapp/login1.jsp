<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="log.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

</head>
<body
	style="background-image: url('imagerestaurant.jpg/log.jpeg'); background-repeat: no-repeat; background-size: 100%">
	<div class="wrapper">
		<h1>Login</h1>
		<form action="LoginServlet" method="post">
			<div class="input-box">
				<input type="email" name="Email" placeholder="user@gmail.com"
					required> <i class='bx bx-user-circle'></i>
			</div>
			<div class="input-box">
				<input type="password" name="password" id="password"
					placeholder="Password" required> <i class="fa fa-eye-slash"
					aria-hidden="true"></i> <i class='bx bx-hide password-toggle'
					id="togglePassword"></i>
			</div>
			<div class="submit-box">
				<input type="submit" value="Login">
			</div>
			<div class="forget-password">
				<p>
					<a href="forgetPassword.jsp">Forget Password ?</a>
				</p>
			</div>
			<div class="signup-link">
				<% if (request.getAttribute("errorMessage") != null) { %>
				<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
				<% } %>
				<p>
					Don't have an account? <a href="signUp1.jsp">SignUp</a>
				</p>
			</div>
		</form>
	</div>

	<script>
        const togglePassword = document.querySelector('#togglePassword');
        const password = document.querySelector('#password');
        togglePassword.addEventListener('click', function (e) {
            // toggle the type attribute
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            // toggle the icon
            this.classList.toggle('bx-show');
        });
    </script>
</body>
</html>