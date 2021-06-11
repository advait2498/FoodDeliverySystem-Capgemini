/**
 * 
 */
package com.cg.fds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.exception.RestaurantNotFoundException;
import com.cg.fds.repository.IItemRepository;
import com.cg.fds.repository.IRestaurantRepository;
import com.cg.fds.service.IRestaurantService;
import java.util.Optional;

/**
 * @author advai
 *
 */
@Service
@Transactional
public class RestaurantServiceImpl implements IRestaurantService {

	@Autowired
	IRestaurantRepository repository;
	
	@Autowired
	IItemRepository item_repository;
	
	@Override
	public Restaurant addRestaurant(Restaurant res) {
		// TODO Auto-generated method stub
		return repository.save(res);
	}

	@Override
	public Restaurant removeRestaurant(int resId) {
		// TODO Auto-generated method stub
		Optional<Restaurant> isRestaurantAvailable = repository.findById(resId);
		if(isRestaurantAvailable.isPresent()) {
			Restaurant restaurantToDelete = isRestaurantAvailable.get();
			repository.delete(restaurantToDelete);
			return restaurantToDelete;
		}else {
			throw new RestaurantNotFoundException("Restaurant not found to update");
		}
	}

	@Override
	public Restaurant updateRestaurant(int resId, Restaurant res) {
		// TODO Auto-generated method stub
		Optional<Restaurant> isRestaurantAvailable = repository.findById(resId);
		if(isRestaurantAvailable.isPresent()) {
			repository.save(res);
			return res;
		}else {
			throw new RestaurantNotFoundException("Restaurant not found to update");
		}
	}

	@Override
	public Restaurant viewRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		Optional<Restaurant> isRestaurantAvailable = repository.findById(restaurantId);
		if(isRestaurantAvailable.isPresent()) {
			return isRestaurantAvailable.get();
		}else {
			throw new RestaurantNotFoundException("Restaurant not found to view");
		}
	}
	@Override
	public Restaurant viewRestaurant(String restaurantName) {
		// TODO Auto-generated method stub
		Restaurant restaurant = repository.findByRestaurantName(restaurantName);
		if(restaurant != null) {
			return restaurant;
		}else {
			throw new RestaurantNotFoundException("Restaurant not found to view");
		}
	}

	@Override
	public List<Restaurant> viewAllRestaurants() {
		// TODO Auto-generated method stub
		return (List<Restaurant>) repository.findAll();
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) {
		// TODO Auto-generated method stub
		return repository.findByAddressContaining(location);
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String name) {
		// TODO Auto-generated method stub
		return item_repository.findRestaurantsByItemName(name);
	}
	
	public List<Item> findByRestaurantName(String name){
		return repository.findItemsByRestaurantName(name);
	}

	@Override
	public List<Item> viewAllItems(String restaurantName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item viewItemOfRestaurant(String restaurantName, String itemName) {
		// TODO Auto-generated method stub
		List<Item> items =repository.findItemsByRestaurantName(restaurantName);
		Optional<Item> isItemFound = items.stream().filter(item->item.getItemName().equalsIgnoreCase(itemName)).findAny();
		if(isItemFound.isPresent())
			return isItemFound.get();
		else
			throw new ItemNotFoundException("No such item in restaurant as queried");
	}


}
