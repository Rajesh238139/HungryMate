package com.tap.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
    private int orderId;
    private int userId; // Foreign key
    private int restaurantId;// Foreign key
    private double totalAmount;
    private Timestamp orderDate;
    private String deliveryAddress;
    private String paymentMethod;
    private String status;
    
    private List<OrderItems> orderItems;
    
    public Order() {
		this.orderDate=new Timestamp(System.currentTimeMillis());
		this.orderItems=new ArrayList<>();
    }

	public Order(int orderId, int userId, int restaurantId, double totalAmount, Timestamp orderDate,
			String deliveryAddress, String paymentMethod, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getUserId() {
		return userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	
	
    
    

}
