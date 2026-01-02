<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.tap.model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile | HungryMate</title>

<!-- Global Styles -->
<link rel="stylesheet" href="styles.css">
</head>

<body>

<%
    User user = (User) session.getAttribute("user");
%>

<% if (user != null) { %>

<div class="profile-container">

    <div class="profile-header">
        <h2>My Profile</h2>
        <p>Manage your personal information</p>
    </div>

    <div class="profile-field">
        <label>Username</label>
        <div class="value"><%= user.getUsername() %></div>
    </div>

    <div class="profile-field">
        <label>Email</label>
        <div class="value"><%= user.getEmail() %></div>
    </div>

    <div class="profile-field">
        <label>Phone Number</label>
        <div class="value"><%= user.getPhoneNumber() %></div>
    </div>

    <div class="profile-actions">
        <a href="restaurant" class="back-btn">← Back to Restaurants</a>
        <a href="logout" class="logout-btn">Logout</a>
    </div>

</div>

<% } else { %>

<div class="not-logged">
    <h3>You are not logged in</h3>
    <a href="login.jsp">Go to Login</a>
</div>

<% } %>

</body>
</html>
