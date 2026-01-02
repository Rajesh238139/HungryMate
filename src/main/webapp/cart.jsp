<%@page import="com.tap.model.CartItem"%>
<%@page import="com.tap.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart | HungryMate</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<h1 class="page-title">Your Cart</h1>

<div class="cart-container">

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");

    if (cart != null && !cart.getItems().isEmpty()) {
        for (CartItem item : cart.getItems().values()) {
%>

    <div class="cart-item">

        <div class="cart-item-details">
            <h3><%= item.getName() %></h3>
            <p>Price: ₹<%= item.getPrice() %></p>
            <p>Total: ₹<%= item.getTotalPrice() %></p>
        </div>

        <div class="quantity-controls">

            <form action="cart">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                <button class="qty-btn plus">+</button>
            </form>

            <strong><%= item.getQuantity() %></strong>

            <form action="cart">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                <button class="qty-btn minus"
                    <%= item.getQuantity() == 1 ? "disabled" : "" %>>−</button>
            </form>

        </div>

        <form action="cart">
            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
            <input type="hidden" name="action" value="remove">
            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
            <button class="remove-btn">Remove</button>
        </form>

    </div>

<%
        }
%>

    <div class="cart-total">
        Grand Total: ₹<%= cart.getTotalPrice() %>
    </div>

    <div class="cart-actions">
        <a class="btn-secondary"
           href="menu?restaurantId=<%= session.getAttribute("oldRestaurantId") %>">
           Add More Items
        </a>

        <form action="checkout.jsp" method="post">
            <button class="btn-primary">Proceed to Checkout</button>
        </form>
    </div>

<%
    } else {
%>

    <div class="empty-cart">
        <p>Your cart is empty.</p>
        <a href="restaurant">Add Items</a>
    </div>

<%
    }
%>

</div>

</body>
</html>
