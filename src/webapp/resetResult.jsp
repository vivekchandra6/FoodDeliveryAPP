<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Reset result</title>
<link rel="stylesheet" href="resetResult.css">
</head>
<body>
	<h2>Password Reset Result</h2>
	<% if(request.getAttribute("message") != null) { %>
	<p class="success"><%= request.getAttribute("message") %></p>
	<% } %>
	<% if(request.getAttribute("error") != null) { %>
	<p class="error"><%= request.getAttribute("error") %></p>
	<% } %>
	<a href="login1.jsp">Go to Login</a>
</body>
</html>