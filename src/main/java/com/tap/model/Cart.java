package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items;

    // Constructor
    public Cart() {
        this.items = new HashMap<>();
    }

    // Add item to cart
    public void addItem(CartItem item) {

        int itemId = item.getItemId();

        if (items.containsKey(itemId)) {

            CartItem existingItem = items.get(itemId);

            int newQty = item.getQuantity();
            int oldQty = existingItem.getQuantity();

            int sumQty = newQty + oldQty;
            existingItem.setQuantity(sumQty);

        } else {
            items.put(itemId, item);
        }
    }

    // Update item quantity
    public void updateItem(int itemId, int quantity) {

        if (items.containsKey(itemId)) {

            if (quantity <= 0) {
                items.remove(itemId);
            } else {
                CartItem existingCartItem = items.get(itemId);
                existingCartItem.setQuantity(quantity);
            }
        }
    }

    // Remove item
    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    // Get all cart items
    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // Get total cart price
    public double getTotalPrice() {
        /*return items.values()
                    .stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();
        */
    	double totalPrice=0;
    	for (CartItem item : items.values()) {
    		totalPrice=totalPrice+item.getPrice()*item.getQuantity();
			
		}
    	return totalPrice;
    }

    // Clear cart
    public void clear() {
        items.clear();
    }
}
