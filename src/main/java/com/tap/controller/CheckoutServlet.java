package com.tap.controller;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.daoimpl.OrderDAOImpl;
import com.tap.daoimpl.OrderItemsDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItems;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");

        String deliveryAddress = req.getParameter("deliveryAddress");
        String paymentMethod = req.getParameter("paymentMethod");

        /* =====================
           USER NOT LOGGED IN
           ===================== */
        if (user == null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return; //  MUST RETURN
        }

        /* =====================
           CART EMPTY
           ===================== */
        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return; //  MUST RETURN
        }

        /* =====================
           CREATE ORDER
           ===================== */
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setRestaurantId(restaurantId);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setDeliveryAddress(deliveryAddress);
        order.setPaymentMethod(paymentMethod);
        order.setStatus("PENDING");

        double totalAmount = 0.0;
        for (CartItem item : cart.getItems().values()) {
            totalAmount += item.getQuantity() * item.getPrice();
        }
        order.setTotalAmount(totalAmount);

        OrderDAOImpl orderDAO = new OrderDAOImpl();
        int orderId = orderDAO.createOrder(order);

        /* =====================
           ORDER ITEMS
           ===================== */
        OrderItemsDAOImpl orderItemsDAO = new OrderItemsDAOImpl();
        for (CartItem item : cart.getItems().values()) {

            OrderItems oi = new OrderItems();
            oi.setOrderId(orderId);
            oi.setItemName(item.getName());
            oi.setOrderItemId(item.getItemId());
            oi.setQuantity(item.getQuantity());
            oi.setTotalPrice(item.getTotalPrice());

            orderItemsDAO.addOrderItem(oi);
        }

        /* =====================
           CLEAN SESSION
           ===================== */
        session.removeAttribute("cart");
        session.removeAttribute("oldRestaurantId");

        /* =====================
           SEND DATA TO CONFIRMATION
           ===================== */
        req.setAttribute("orderId", orderId);
        req.setAttribute("totalAmount", totalAmount);
        req.setAttribute("paymentMethod", paymentMethod);
        req.setAttribute("deliveryAddress", deliveryAddress);

        req.getRequestDispatcher("orderConfirmation.jsp").forward(req, resp);
    }
}
