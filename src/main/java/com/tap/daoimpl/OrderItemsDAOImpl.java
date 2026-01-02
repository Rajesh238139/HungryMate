package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemsDAO;
import com.tap.model.OrderItems;
import com.tap.util.DBConnection;

public class OrderItemsDAOImpl implements OrderItemsDAO {

    private static final String INSERT_ITEM =
        "INSERT INTO order_items (order_id, item_name, qty, total_price) VALUES (?, ?, ?, ?)";

    private static final String GET_ITEMS_BY_ORDER =
        "SELECT * FROM order_items WHERE order_id = ?";

    @Override
    public boolean addOrderItem(OrderItems item) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ITEM)) {

            ps.setInt(1, item.getOrderId());
            ps.setString(2, item.getItemName());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getTotalPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<OrderItems> getItemsByOrderId(int orderId) {

        List<OrderItems> items = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ITEMS_BY_ORDER)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItems item = new OrderItems();
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("qty"));
                item.setTotalPrice(rs.getDouble("total_price"));

                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}
