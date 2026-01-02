package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.util.DBConnection;

public class MenuDAOImpl implements MenuDAO{
	private static final String INSERT =
	        "INSERT INTO menu(restaurant_id,name,description,price,rating,image_url,type,is_available) VALUES (?,?,?,?,?,?,?,?)";

	    private static final String GET_BY_ID =
	        "SELECT * FROM menu WHERE menu_id=?";

	    private static final String GET_BY_RESTAURANT =
	        "SELECT * FROM menu WHERE restaurant_id=?";

	    private static final String GET_ALL =
	        "SELECT * FROM menu";

	    private static final String UPDATE =
	        "UPDATE menu SET name=?, description=?, price=?, rating=?, image_url=?, type=?, is_available=? WHERE menu_id=?";

	    private static final String DELETE =
	        "DELETE FROM menu WHERE menu_id=?";

		@Override
		public int addMenuItem(Menu menuItem) {
			int res=0;
			try (Connection con = DBConnection.getConnection();
			     PreparedStatement ps = con.prepareStatement(INSERT)) {

			        ps.setInt(1, menuItem.getRestaurantId());
			        ps.setString(2, menuItem.getName());
			        ps.setString(3, menuItem.getDescription());
			        ps.setDouble(4, menuItem.getPrice());
			        ps.setDouble(5, menuItem.getRating());
			        ps.setString(6, menuItem.getImageUrl());
			        ps.setString(7, menuItem.getType());
			        ps.setBoolean(8, menuItem.isAvailable());

			        res = ps.executeUpdate();
			        
			        
			    } catch (Exception e) { e.printStackTrace(); }
			return res;
		}

		@Override
		public Menu getMenuById(int menuId) {
			try (Connection con = DBConnection.getConnection();
			     PreparedStatement ps = con.prepareStatement(GET_BY_ID)) {

			     ps.setInt(1, menuId);
			     ResultSet rs = ps.executeQuery();
			     if (rs.next()) 
			     {
			    	 return mapMenu(rs);
			     }

			} 
			catch (Exception e) { 
				e.printStackTrace(); 
			}
			return null;
		}

		@Override
		public List<Menu> getMenuByRestaurant(int restaurantId) {
			List<Menu> list = new ArrayList<>();
		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(GET_BY_RESTAURANT)) {

		        ps.setInt(1, restaurantId);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next())
		        {
		        	list.add(mapMenu(rs));
		        }

		    } 
		    catch (Exception e) { 
		    	e.printStackTrace(); 
		    }
		    return list;
		}

		@Override
		public void updateMenu(Menu menuItem) {
			try (Connection con = DBConnection.getConnection();
			         PreparedStatement ps = con.prepareStatement(UPDATE)) {

			        ps.setString(1, menuItem.getName());
			        ps.setString(2, menuItem.getDescription());
			        ps.setDouble(3, menuItem.getPrice());
			        ps.setDouble(4, menuItem.getRating());
			        ps.setString(5, menuItem.getImageUrl());
			        ps.setString(6, menuItem.getType());
			        ps.setBoolean(7, menuItem.isAvailable());
			        ps.setInt(8, menuItem.getMenuId());

			        int res = ps.executeUpdate();
			        System.out.println(res);
			        
			} 
			catch (Exception e) { 
				e.printStackTrace(); 
			}
		}

		@Override
		public void deleteMenu(int menuId) {
			 try (Connection con = DBConnection.getConnection();
			      PreparedStatement ps = con.prepareStatement(DELETE)) {

			      ps.setInt(1, menuId);
			      int res = ps.executeUpdate();
			      System.out.println(res);
			  } 
			 catch (Exception e) { 
				 e.printStackTrace(); 
			 }
			
		}

		@Override
		public List<Menu> getAllMenuItems() {
			List<Menu> list = new ArrayList<>();
		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(GET_ALL)) {

		        ResultSet rs = ps.executeQuery();
		        while (rs.next())
		        {
		        	list.add(mapMenu(rs));
		        }

		    } 
		    catch (Exception e) { 
		    	e.printStackTrace(); 
		    }
		    return list;
		}
		
		
		private Menu mapMenu(ResultSet rs) throws SQLException {
		    Menu menuItem = new Menu();
		    menuItem.setMenuId(rs.getInt("menu_id"));
		    menuItem.setRestaurantId(rs.getInt("restaurant_id"));
		    menuItem.setName(rs.getString("name"));
		    menuItem.setDescription(rs.getString("description"));
		    menuItem.setPrice(rs.getDouble("price"));
		    menuItem.setRating(rs.getDouble("rating"));
		    menuItem.setImageUrl(rs.getString("image_url"));
		    menuItem.setType(rs.getString("type"));
		    menuItem.setAvailable(rs.getBoolean("is_available"));
		    return menuItem;
		}
		
		


	    
}






