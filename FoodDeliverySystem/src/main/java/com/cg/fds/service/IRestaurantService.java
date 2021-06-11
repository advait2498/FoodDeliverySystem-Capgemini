package com.cg.fds.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

@Service
@Transactional
public interface IRestaurantService {

	public Restaurant addRestaurant(Restaurant res);
	public Restaurant removeRestaurant(int restaurantId);
	public Restaurant updateRestaurant(int restaurantId, Restaurant res);
	public Restaurant viewRestaurant(int restaurantId);
	public Restaurant viewRestaurant(String restaurantName);
	public List<Item> viewAllItems(String restaurantName);
	public Item viewItemOfRestaurant(String restaurantName, String itemName);
	public List<Restaurant> viewAllRestaurants();
	public List<Restaurant> viewNearByRestaurant(String location);
	public List<Restaurant> viewRestaurantByItemName(String name);
}
