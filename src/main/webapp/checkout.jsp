<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.tap.model.Cart, com.tap.model.CartItem, java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout | HungryMate</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<h1 class="page-title">Checkout</h1>

<div class="checkout-container">

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");

    if (cart != null && !cart.getItems().isEmpty()) {
%>

    <!-- ORDER SUMMARY -->
    <div class="section">
        <h3>Order Summary</h3>

        <%
            for (CartItem item : cart.getItems().values()) {
        %>
            <div class="order-item">
                <span><%= item.getName() %> × <%= item.getQuantity() %></span>
                <span>&#8377;<%= item.getTotalPrice() %></span>
            </div>
        <%
            }
        %>

        <div class="total">
            Grand Total: &#8377;<%= cart.getTotalPrice() %>
        </div>
    </div>

    <!-- DELIVERY & PAYMENT -->
    <form action="checkout" method="post">

        <div class="section">
            <h3>Delivery Address</h3>
            <textarea name="deliveryAddress" required
                placeholder="Enter your complete delivery address"></textarea>
        </div>

        <div class="section">
            <h3>Payment Method</h3>
            <div class="payment-options">
                <label>
                    <input type="radio" name="paymentMethod" value="COD" checked>
                    Cash on Delivery
                </label>
                <label>
                    <input type="radio" name="paymentMethod" value="ONLINE">
                    Online Payment
                </label>
            </div>
        </div>

        <!-- Hidden values -->
        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
        <input type="hidden" name="totalAmount" value="<%= cart.getTotalPrice() %>">

        <div class="actions">
            <a class="back-btn" href="cart.jsp">Back to Cart</a>
            <button class="place-order-btn">Place Order</button>
        </div>

    </form>

<%
    } else {
%>

    <!-- EMPTY CART -->
    <div class="empty-cart">
        <h2>Your cart is empty</h2>
        <p>Add items to proceed with checkout</p>
        <a class="empty-cart-btn" href="restaurant">Browse Restaurants</a>
    </div>

<%
    }
%>

</div>

</body>
</html>
