<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.fooddilivery.module.Restaurant, com.fooddilivery.module.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Menu</title>
<link rel="stylesheet" href="addMenu.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
			<% 
                User admin = (User) session.getAttribute("admin");
                String adminName = admin != null ? admin.getName() : "Guest";
            %>
	<header>
    <nav class="navbar">
        <div class="logo">
            <img src="https://dynamic.brandcrowd.com/preview/logodraft/844e35ef-0b11-40fa-849a-b02d8eef1832/image/large.png" alt="DevFooday App" />
        </div>
        <div class="dropdown">
            <button class="dropbtn">
                <i class="fas fa-user-circle"></i> <%= adminName %> <i class="fas fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <a href="profile.jsp"><i class="fas fa-user-edit"></i> Profile</a>
                <a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </div>
        </div>
    </nav>
</header>


	<div class="container">
		<h2>Add New Menu Item</h2>

		<form action="AddMenuServlet" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="restaurantId"
				value="<%= request.getParameter("restaurantId") %>"> <label
				for="itemName">Item Name:</label> <input type="text" id="itemName"
				name="itemName" required><br>
			<br> <label for="description">Description:</label>
			<textarea id="description" name="description" rows="4" required></textarea>
			<br>
			<br> <label for="price">Price (₹):</label> <input type="number"
				id="price" name="price" step="0.01" required><br>
			<br> <label for="rating">Rating:</label> <input type="number"
				id="rating" name="rating" step="0.1" max="5" min="0" required><br>
			<br> <label for="isAvailable">Availability:</label> <select
				id="isAvailable" name="isAvailable" required>
				<option value="Yes">Yes</option>
				<option value="No">No</option>
			</select><br>
			<br> <label for="imagePath">Image:</label> <input type="file"
				id="imagePath" name="imagePath" required><br>
			<br> <input type="submit" value="Add Menu Item" class="btn">
		</form>

		<div class="actions">
			<a href="AdminDashBoard" class="btn">Back to Dashboard</a>
		</div>
	</div>
</body>
</html>
