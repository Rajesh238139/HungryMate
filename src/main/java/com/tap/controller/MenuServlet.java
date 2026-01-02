package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("Menu servlet called...");
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		
		List<Menu> menuByRestaurant = menuDAOImpl.getMenuByRestaurant(restaurantId);
		
		for (Menu menu : menuByRestaurant) {
			System.out.println(menu);
		}
		
		req.setAttribute("menuByRestaurant", menuByRestaurant);
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
	}
	

}
