package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

public interface IRestaurantRepository extends CrudRepository<Restaurant, Integer>{

	//public Restaurant addRestaurant(Restaurant res);
	//public Restaurant removeRestaurant(Restaurant res);
	//public Restaurant updateRestaurant(Restaurant res);
	@Query("select rest.itemList from Restaurant rest where rest.restaurantName = ?1")
	public List<Item> findItemsByRestaurantName(String name);
	
	public Restaurant findByRestaurantName(String name);
	//public Restaurant findByRestaurant(Restaurant res);
	public List<Restaurant> findAll();
	public List<Restaurant> findByAddressContaining(String location);
	//public List<Restaurant> findByItemNameConta(String name);
}
