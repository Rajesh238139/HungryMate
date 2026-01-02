package com.tap.model;

public class OrderItems {
	private int orderItemId;
    private int orderId;//Foreignkey
    private String itemName;
    private int quantity;
    private double itemPrice;
    private double totalPrice;
    
    private Menu menuItems;// Optional : for relationship
    
    public OrderItems() {
		
	}
	public OrderItems(int orderItemId, int orderId, String itemName, int quantity, double itemPrice,
			double totalPrice) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.totalPrice = totalPrice;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public String getItemName() {
		return itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
    
    
}
