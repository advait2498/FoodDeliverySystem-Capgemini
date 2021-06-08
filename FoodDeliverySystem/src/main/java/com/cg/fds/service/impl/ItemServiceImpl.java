/**
 * 
 */
package com.cg.fds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
	public Item viewItem(String id) {
		// TODO Auto-generated method stub
		return repository.findByItemId(Integer.parseInt(id));
	}

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		Optional<Item> isItemAvailable = repository.findById(item.getItemId());
		if(isItemAvailable.isPresent()) {
			return isItemAvailable.get();
		}else {
			throw new ItemNotFoundException("Item not found");
		}
	}

	@Override
	public void removeItem(String id) {
		// TODO Auto-generated method stub
		Optional<Item> isItemAvailable = repository.findById(Integer.parseInt(id));
		if(isItemAvailable.isPresent())
			repository.delete(isItemAvailable.get());
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

}
