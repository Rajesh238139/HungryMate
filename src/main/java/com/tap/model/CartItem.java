package com.tap.model;

public class CartItem {

    private int itemId;
    private String name;
    private int quantity;
    private double price;

    // -------- Constructor --------
    public CartItem(int itemId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // -------- Getters --------
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // -------- Setters --------
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // -------- Utility Method --------
    public double getTotalPrice() {
        return price * quantity;
    }
}
