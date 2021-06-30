/**
 * 
 */
package com.cg.fds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
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
	public Restaurant removeRestaurant(Restaurant res) {
		// TODO Auto-generated method stub
		repository.delete(res);
		return res;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res) {
		// TODO Auto-generated method stub
		Optional<Restaurant> isRestaurantAvailable = repository.findById(res.getRestaurantId());
		if(isRestaurantAvailable.isPresent()) {
			repository.save(res);
			return res;
		}else {
			throw new RestaurantNotFoundException("Restaurant not found to update");
		}
	}

	@Override
	public Restaurant viewRestaurant(String name) {
		// TODO Auto-generated method stub
		Restaurant restaurantToView = repository.findByRestaurantName(name);
		if(restaurantToView != null) {
			return restaurantToView;
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

	@Override
	public List<Item> viewAllItemsByRestaurant(String name) {
		// TODO Auto-generated method stub
		return repository.findItemsByRestaurantName(name);
	}

}
