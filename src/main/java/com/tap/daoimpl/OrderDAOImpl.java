package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER =
        "INSERT INTO orders (user_id, restaurant_id, total_amount, address, payment_method, status) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_ORDER_BY_ID =
        "SELECT * FROM orders WHERE order_id = ?";

    private static final String GET_ORDERS_BY_USER =
        "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

    private static final String UPDATE_ORDER_STATUS =
        "UPDATE orders SET status = ? WHERE order_id = ?";

    @Override
    public int createOrder(Order order) {

        int orderId = 0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                 con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getDeliveryAddress());
            ps.setString(5, order.getPaymentMethod());
            ps.setString(6, order.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1); // auto-generated order_id
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ORDER_BY_ID)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = mapOrder(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ORDERS_BY_USER)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(mapOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ORDER_STATUS)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Helper method
    private Order mapOrder(ResultSet rs) throws SQLException {

        Order order = new Order();

        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setRestaurantId(rs.getInt("restaurant_id"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        order.setDeliveryAddress(rs.getString("address"));
        order.setPaymentMethod(rs.getString("payment_method"));
        order.setStatus(rs.getString("status"));

        return order;
    }
}
