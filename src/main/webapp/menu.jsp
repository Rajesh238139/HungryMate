<%@page import="com.tap.model.Menu,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HungryMate | Menu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Global Styles -->
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<!-- ===== MENU HEADER ===== -->
<header class="menu-header">
    <h1>Restaurant Menu</h1>
    <p>Delicious food, freshly prepared</p>

    <!-- Back Button -->
    <a href="restaurant" class="back-btn">← Back to Restaurants</a>
</header>

<main>
    <section class="menu-grid">

        <%
            List<Menu> menuByRestaurant =
                    (List<Menu>) request.getAttribute("menuByRestaurant");

            for (Menu menu : menuByRestaurant) {
        %>

        <article class="menu-card">
            <img src="<%= menu.getImageUrl() %>" alt="<%= menu.getName() %>">

            <div class="menu-content">

                <h3 class="menu-title"><%= menu.getName() %></h3>

                <p class="menu-desc"><%= menu.getDescription() %></p>

                <div class="menu-meta">
                    <span class="menu-rating">⭐ <%= menu.getRating() %></span>
                    <span class="menu-price">₹<%= menu.getPrice() %></span>
                </div>

                <form action="cart" class="add-cart-form">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                    <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>">
                    <input type="hidden" name="quantity" value="1">
                    <input type="hidden" name="action" value="add">

                    <button type="submit" class="add-cart-btn">
                        Add to Cart
                    </button>
                </form>

            </div>
        </article>

        <%
            }
        %>

    </section>
</main>

</body>
</html>
