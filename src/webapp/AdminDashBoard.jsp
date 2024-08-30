<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.fooddilivery.module.Restaurant, com.fooddilivery.module.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="adminDashBoard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <% 
            User admin = (User) session.getAttribute("admin");
            String adminName = admin != null ? admin.getName() : "Guest";
        %>
        <nav class="navbar">
            <div class="logo">
                <h1>Admin Dashboard</h1>
            </div>
            <div class="dropdown">
                <button class="dropbtn">
                    <i class="fas fa-user-circle"></i>  Welcome <%= adminName %> <i class="fas fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="profile.jsp"><i class="fas fa-user-edit"></i> Profile</a>
                    <a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </div>
            </div>
        </nav>
    </header>

    <div class="container">
        <h2>Your Restaurants</h2>
        <%
            if (admin != null) {
                List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurantList");
                if (restaurants != null && !restaurants.isEmpty()) {
        %>
        <div class="restaurant-list">
            <ul>
                <%
                    for (Restaurant restaurant : restaurants) {
                %>
                <li class="restaurant-item">
                    <img alt="<%= restaurant.getName() %>" src="<%= restaurant.getImagePath() %>">
                    <div class="restaurant-details">
                        <h3><%= restaurant.getName() %></h3>
                        <p><strong>Address:</strong> <%= restaurant.getAdress() %></p>
                        <p><strong>Phone:</strong> <%= restaurant.getPhoneNum() %></p>
                        <p><strong>Rating:</strong> <%= restaurant.getRating() %></p>
                        <p><strong>Cuisine Type:</strong> <%= restaurant.getCousineType() %></p>
                        <p><strong>ETA:</strong> <%= restaurant.getETA() %> minutes</p>
                        <div class="buttons">
                            <a href="ViewMenus?restaurantId=<%= restaurant.getResturantId()%>" class="btn">View Menus</a>
                            <a href="AddMenu.jsp?restaurantId=<%= restaurant.getResturantId()%>" class="btn">Add Menu</a>
                        </div>
                    </div>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
        <%
                } else {
        %>
        <p>You don't have any restaurants yet.</p>
        <%
                }
            }
        %>

        <div class="actions">
            <a href="AddRestaurant.jsp" class="btn add-btn">Add New Restaurant</a>
        </div>
    </div>
</body>
</html>
