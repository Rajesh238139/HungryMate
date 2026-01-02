<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.tap.model.Restaurant,java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HungryMate | Restaurants</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="app.png">
    <!-- Global Styles -->
    <link rel="stylesheet" href="styles.css">
</head>

<body>

<!-- ================= HEADER ================= -->
<header>
    <nav>
        <div class="logo">
            <img src="https://cdn-icons-png.flaticon.com/512/3075/3075977.png" alt="HungryMate Logo">
            HungryMate
        </div>

        <div class="location-box" onclick="openCityPopup()">
            <span>📍</span>
            <strong id="current-city">Detecting...</strong>
        </div>

        <div class="header-search">
            <input type="text" placeholder="Search restaurants or dishes...">
            <span>🔍</span>
        </div>

        <div class="nav-links">
            <a href="restaurant">Home</a>
            <a href="login.jsp">Login</a>
            <a href="cart.jsp">Cart</a>
            <a href="profile.jsp">Profile</a>
        </div>
    </nav>
</header>

<!-- ================= MAIN ================= -->
<main>

    <section aria-label="Restaurant Listings">
        <h2>Popular Restaurants Near You</h2>

        <div class="restaurant-grid">

            <%
                List<Restaurant> allRestaurants =
                        (List<Restaurant>) request.getAttribute("allRestaurants");

                if (allRestaurants != null && !allRestaurants.isEmpty()) {
                    for (Restaurant restaurant : allRestaurants) {
            %>

            <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>">

    <div class="restaurant-card">
        <img src="<%= restaurant.getImageUrl() %>"
             alt="<%= restaurant.getName() %>">

        <div class="card-content">

            <div class="card-header">
                <h3><%= restaurant.getName() %></h3>
                <span class="rating"><%= restaurant.getRating() %> ★</span>
            </div>

            <!-- UNIQUE META INFO -->
            <div class="restaurant-meta">

                <span class="meta-pill eta-pill">
                    ⏱ <%= restaurant.getEta() %> mins
                </span>

                <span class="meta-pill cuisine-pill">
                    🍽 <%= restaurant.getCuisineType() %>
                </span>

            </div>

            <p class="restaurant-address">
                📍 <%= restaurant.getAddress() %>
            </p>

        </div>
    </div>

</a>
            
            <%
                    }
                } else {
            %>

            <p style="padding:40px;">No restaurants found</p>

            <%
                }
            %>

        </div>
    </section>

</main>

<!-- ================= FOOTER ================= -->
<footer>
    © 2025 HungryMate • Fresh food delivered fast
</footer>

<!-- ================= LOCATION SCRIPT ================= -->
<script>
function detectLocation() {
    if (!navigator.geolocation) {
        alert("Geolocation not supported");
        return;
    }

    navigator.geolocation.getCurrentPosition(success, error);

    function success(position) {
        const lat = position.coords.latitude;
        const lon = position.coords.longitude;

        fetch(`https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lon}`)
            .then(res => res.json())
            .then(data => {
                const city =
                    data.address.city ||
                    data.address.town ||
                    data.address.village ||
                    "Your Location";

                document.getElementById("current-city").innerText = city;
            });
    }

    function error() {
        alert("Location access denied");
    }
}

window.onload = detectLocation;
</script>

<!-- ================= CITY POPUP ================= -->
<div id="city-popup" class="popup-overlay">
    <div class="popup">
        <h3>Select City</h3>
        <ul>
            <li onclick="setCity('Hyderabad')">Hyderabad</li>
            <li onclick="setCity('Bangalore')">Bangalore</li>
            <li onclick="setCity('Chennai')">Chennai</li>
            <li onclick="setCity('Mumbai')">Mumbai</li>
        </ul>
    </div>
</div>

<script>
function openCityPopup() {
    document.getElementById("city-popup").style.display = "flex";
}

function setCity(city) {
    document.getElementById("current-city").innerText = city;
    document.getElementById("city-popup").style.display = "none";
}
</script>

</body>
</html>
