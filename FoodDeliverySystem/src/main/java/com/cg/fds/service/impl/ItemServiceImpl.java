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
import com.cg.fds.repository.IItemRepository;
import com.cg.fds.repository.IRestaurantRepository;
import com.cg.fds.service.IItemService;
import java.util.Optional;

import javax.persistence.Query;

/**
 * @author advai
 *
 */
@Service
@Transactional
public class ItemServiceImpl implements IItemService {

	@Autowired
	IItemRepository repository;
	
	@Autowired
	IRestaurantRepository restaurant_repo;
	
	@Override
	public Item addItem(Item item) {
		// TODO Auto-generated method stub
		return repository.save(item);
	}

	@Override
	public Item viewItem(int id) {
		// TODO Auto-generated method stub
		Optional<Item> isItemAvailable = repository.findById(id);
		if(isItemAvailable.isPresent())
			return isItemAvailable.get();
		else
			throw new ItemNotFoundException("Item not found");
	}

	@Override
	public Item updateItem(int id, Item item) {
		// TODO Auto-generated method stub
		Optional<Item> isItemAvailable = repository.findById(id);
		if(isItemAvailable.isPresent()) {
			repository.save(item);
			return item;
		}else {
			throw new ItemNotFoundException("Item not found");
		}
	}

	@Override
    public Item removeItem(int id,Item item) {
        // TODO Auto-generated method stub
        Optional<Item> isItemAvailable = repository.findById(id);
        if(isItemAvailable.isPresent()) {
            repository.delete(item);
        	return item;
		}
        else
            throw new ItemNotFoundException("Item not found");
	}

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		// TODO Auto-generated method stub
		return restaurant_repo.findItemsByRestaurantName(res.getRestaurantName());
	}

	@Override
	public List<Item> viewAllItems(Category cat) {
		// TODO Auto-generated method stub
		return repository.findByCategory(cat);
	}

	@Override
	public List<Item> viewAllItemsByName(String name) {
		// TODO Auto-generated method stub
		return (List<Item>) repository.findAll();
	}

	public List<Restaurant> findRestaurantsByItemName(String itemName) {
		// TODO Auto-generated method stub
		return repository.findRestaurantsByItemName(itemName);
	}

	@Override
	public Item updateItem(String itemName) {
		// TODO Auto-generated method stub
		return repository.findByItemName(itemName);
	}
	
	@Override
	public Item viewItem(String name) {
		return repository.findByItemName(name);
	}

	@Override
	public List<Item> searchByPriceBetween(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return repository.findByCostBetween(minPrice, maxPrice);
	}

	@Override
	public List<Item> searchByCategory(Category cat) {
		// TODO Auto-generated method stub
		return repository.searchByCategory(cat);
	}

}
