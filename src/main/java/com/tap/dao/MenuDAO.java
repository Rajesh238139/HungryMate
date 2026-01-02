package com.tap.dao;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDAO {
	
	int addMenuItem(Menu menuItem);
	Menu getMenuById(int menuId);
	List<Menu> getMenuByRestaurant(int restaurantId);
	void updateMenu(Menu menuItem);
	void deleteMenu(int menuId);
	List<Menu> getAllMenuItems();

}


