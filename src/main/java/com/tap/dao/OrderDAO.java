package com.tap.dao;

import java.util.List;
import com.tap.model.Order;

public interface OrderDAO {

    int createOrder(Order order);        // returns generated order_id

    Order getOrderById(int orderId);

    List<Order> getOrdersByUserId(int userId);

    boolean updateOrderStatus(int orderId, String status);
}
