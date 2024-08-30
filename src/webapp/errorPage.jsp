<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<link rel="stylesheet" href="errorpage.css">
<link rel="icon"
	href="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png">

</head>
<body>
	<div class="error-container">
		<h1>Oops! Something went wrong</h1>
		<%  String userErrorMessage = (String) session.getAttribute("userErrorMessage");
             if (userErrorMessage != null) { %>
		<div id="message" class="error-message">
			<%=userErrorMessage %>
		</div>
		<a href="login1.jsp" class="login-here">Login here</a>
		<% session.removeAttribute("userErrorMessage"); // Remove the message after displaying it
       		       session.removeAttribute("cart");
        		} else if (request.getAttribute("error") != null) { %>
		<div id="message" class="error-message">
			<%= request.getAttribute("error") %>
		</div>
		<% } else { %>
		<div id="message" class="error-message">An unexpected error
			occurred. Please try again later.</div>
		<% } %>
		<a href="home" class="back-home">Back to Home</a>
	</div>
</body>
</html>
