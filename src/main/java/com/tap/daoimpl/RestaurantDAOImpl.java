package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO{
	
	private static final String INSERT_RESTAURANT="INSERT INTO restaurant(restaurant_id,name,admin_id,address,cuisine_type,rating,eta,image_url,is_available)"
			+ " VALUES(?,?,?,?,?,?,?,?,?)"; 

	private static final String GET_BY_RESTAURANT_ID ="SELECT * FROM restaurant WHERE restaurant_id=?";
	private static final String UPDATE_RESTAURANT_INFO = "UPDATE restaurant SET name=?,address=?,cuisine_type=?,rating=?,eta=?,image_url=?,is_available=?"
			+ " WHERE restaurant_id=?"; 
	
	private static final String DELETE_RESTAURANT="DELETE FROM restaurant WHERE restaurant_id=?";
	private static final String GET_ALL_RESTAURANTS ="SELECT * FROM restaurant";
	
	@Override
	public int addRestaurant(Restaurant restaurant) {
		
		int res=0;
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT);) {
			preparedStatement.setInt(1, restaurant.getRestaurantId());
			preparedStatement.setString(2, restaurant.getName());
			preparedStatement.setInt(3, restaurant.getAdminId());
			preparedStatement.setString(4, restaurant.getAddress());
			preparedStatement.setString(5, restaurant.getCuisineType());
			preparedStatement.setDouble(6, restaurant.getRating());
			preparedStatement.setInt(7, restaurant.getEta());
			preparedStatement.setString(8, restaurant.getImageUrl());
			preparedStatement.setBoolean(9, restaurant.isAvailable());
			
			res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Restaurant restaurant = null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_RESTAURANT_ID);) {
				preparedStatement.setInt(1, restaurantId);
				
				ResultSet res = preparedStatement.executeQuery();
				
				while(res.next())
				{
					int restaurantid = res.getInt("restaurant_id");
					String name = res.getString("name");
					int adminId = res.getInt("admin_id");
					String address = res.getString("address");
					String cuisineType = res.getString("cuisine_type");
					double rating = res.getDouble("rating");
					int ETA = res.getInt("eta");
					String imageUrl = res.getString("image_url");
					boolean isAvailable = res.getBoolean("is_available");
					
					restaurant = new Restaurant(restaurantid, name, adminId, address, cuisineType, rating, ETA, imageUrl, isAvailable);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_INFO);) {
				preparedStatement.setString(1, restaurant.getName());
				preparedStatement.setString(2, restaurant.getAddress());
				preparedStatement.setString(3, restaurant.getCuisineType());
				preparedStatement.setDouble(4, restaurant.getRating());
				preparedStatement.setInt(5, restaurant.getEta());
				preparedStatement.setString(6, restaurant.getImageUrl());
				preparedStatement.setBoolean(7, restaurant.isAvailable());
				preparedStatement.setInt(8, restaurant.getRestaurantId());
				
				int res = preparedStatement.executeUpdate();
				
				System.out.println(res);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT);) {
				preparedStatement.setInt(1, restaurantId);
				
				int res = preparedStatement.executeUpdate();
				
				System.out.println(res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESTAURANTS);) {
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next())
			{
				int restaurantid = res.getInt("restaurant_id");
				String name = res.getString("name");
				int adminId = res.getInt("admin_id");
				String address = res.getString("address");
				String cuisineType = res.getString("cuisine_type");
				double rating = res.getDouble("rating");
				int ETA = res.getInt("eta");
				String imageUrl = res.getString("image_url");
				boolean isAvailable = res.getBoolean("is_available");
				
				Restaurant restaurant = new Restaurant(restaurantid, name, adminId, address, cuisineType, rating, ETA, imageUrl, isAvailable);
				listOfRestaurants.add(restaurant);
			}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return listOfRestaurants;
	}

}
