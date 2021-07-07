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
	public Restaurant removeRestaurant(Restaurant res);
	public Restaurant updateRestaurant(Restaurant res);
	public Restaurant viewRestaurant(String name);
	public List<Item> viewAllItemsByRestaurant(String name);
	public List<Restaurant> viewAllRestaurants();
	public List<Restaurant> viewNearByRestaurant(String location);
	public List<Restaurant> viewRestaurantByItemName(String name);
}
