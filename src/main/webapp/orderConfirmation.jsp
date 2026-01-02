<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Confirmed | HungryMate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Global Styles -->
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<%
    Integer orderId = (Integer) request.getAttribute("orderId");
    Double totalAmount = (Double) request.getAttribute("totalAmount");
    String paymentMethod = (String) request.getAttribute("paymentMethod");
    String deliveryAddress = (String) request.getAttribute("deliveryAddress");
%>

<div class="order-confirmation-wrapper">

    <div class="confirmation-box">

        <div class="success-icon">&#9989;</div>

        <h1>Order Confirmed!</h1>
        <p class="thank-you">
            Thank you for ordering with <strong>HungryMate</strong>
        </p>

        <div class="order-info">
            <p>
                <span class="label">Order ID:</span>
                <span class="value">
                    <%= orderId != null ? orderId : "Generated Soon" %>
                </span>
            </p>

            <p>
                <span class="label">Total Amount:</span>
                <span class="value price">
                    &#8377;<%= totalAmount != null ? String.format("%.2f", totalAmount) : "0.00" %>
                </span>
            </p>

            <p>
                <span class="label">Payment Method:</span>
                <span class="value"><%= paymentMethod %></span>
            </p>

            <p>
                <span class="label">Delivery Address:</span><br>
                <span class="value address"><%= deliveryAddress %></span>
            </p>

            <p>
                <span class="label">Estimated Delivery:</span>
                <span class="value">30 – 45 minutes</span>
            </p>
        </div>

        <div class="confirmation-actions">
            <a href="restaurant" class="btn dark-btn">Order More Food</a>
            <a href="restaurant" class="btn primary-btn">Go to Home</a>
        </div>

    </div>

</div>

</body>
</html>
