package com.tap.dao;

import java.util.List;
import com.tap.model.OrderItems;

public interface OrderItemsDAO {

    boolean addOrderItem(OrderItems item);

    List<OrderItems> getItemsByOrderId(int orderId);
}
