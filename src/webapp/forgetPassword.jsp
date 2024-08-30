<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forget-Password</title>
<link rel="stylesheet" href="Passwordreset.css">
</head>
<body>
	<form action="ForgotPassword" method="post">
		<%
		if (request.getAttribute("message") != null) {
		%>
		<div id="message" class="<%=request.getAttribute("messageType")%>">
			<%=request.getAttribute("message")%>
		</div>
		<%
		}
		%>

		<label for="email">Email:</label> <input type="email" id="email"
			name="email" required> <input type="submit"
			value="Reset Password">
	</form>

</body>
</html>