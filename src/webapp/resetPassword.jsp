<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Reset Password</title>
<link rel="stylesheet" href="resetPassword.css">
</head>
<body>
	<h2>Reset Password</h2>
	<%
		String email = (String) session.getAttribute("email");
        String token = (String) session.getAttribute("resettoken");
    	String errorMessage = (String) request.getAttribute("errorPassword");

        if (token == null || token.isEmpty()) { %>

	<p class="error">Invalid or expired reset link. Please request a
		new password reset.</p>

	<% }else{ %>
	<%  if (errorMessage != null && !errorMessage.isEmpty()) { %>
	<p class="error"><%=errorMessage%></p>
	<% } %>
	<form action="updatePassword" method="post">
		<input type="hidden" name="email" value="${sessionScope.email}" /> <label
			for="newPassword">New Password:</label> <input type="password"
			id="newPassword" name="newPassword" required /> <br> <br>
		<label for="confirmPassword">Confirm New Password:</label> <input
			type="password" id="confirmPassword" name="confirmPassword" required />
		<br> <br>
		<button type="submit">Reset Password</button>
	</form>
	<%} %>
</body>
</html>
