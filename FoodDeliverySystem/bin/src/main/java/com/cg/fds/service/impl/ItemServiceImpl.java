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
import com.cg.fds.exception.ItemListNotFoundException;
import com.cg.fds.exception.ItemNotFoundException;
import com.cg.fds.repository.IItemRepository;
import com.cg.fds.repository.IRestaurantRepository;
import com.cg.fds.service.IItemService;
import com.cg.fds.service.IRestaurantService;

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
	IRestaurantRepository restaurantRepo;
	
	IRestaurantService restaurantService;

	@Override
	public Item addItem(Item item) {
		return repository.save(item);
	}

	@Override
	public Item viewItem(int id) {
		Optional<Item> isItemAvailable = repository.findById(id);
		if(isItemAvailable.isPresent())
			return isItemAvailable.get();
		else
			throw new ItemNotFoundException("Item not found");
	}

	@Override
	public Item updateItem(int id, Item item) {
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
		return restaurantRepo.findItemsByRestaurantName(res.getRestaurantName());
	}

	@Override
	public List<Item> viewAllItems(Category cat) {
		return repository.findByCategory(cat);
	}

	@Override
	public List<Item> viewAllItemsByName(String name) {
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
	public Item viewItems(String name) {
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

	@Override
	public List<Item> findByItemNameStartsWith(String itemName) {
		List<Item> item = repository.findByItemNameStartsWith(itemName);
     	if(item.size()!= 0)
			return item;
     	else
     		throw new ItemNotFoundException("Item not found");
	}

	@Override
	public List<Item> findByCostGreaterThanEqual(float cost) 
	{
		List<Item> item = repository.findByCostGreaterThanEqual(cost);
		if(item.size()!=0)
			return item;
		else
			throw new ItemNotFoundException("Item Not Found");
	}

	@Override
	public List<Item> findByCategoryOrderByItemName(Category cat) {
		List<Item> item = repository.findByCategoryOrderByItemName(cat);
		if(item.size()!=0)
			return item;
		else
			throw new ItemNotFoundException("Item Not Found");			
	}	
	
	@Override
	public List<Item> findByCostBetween(float minCost, float maxCost) {
        // TODO Auto-generated method stub
        List<Item> item = repository.findByCostBetween(minCost,maxCost);
        if(minCost>maxCost || item.size() == 0)
        	throw new ItemNotFoundException("Item Not Found");
        return item;
        
    }
	
	@Override
	public List<Item> findItemListByRestaurant(String restaurantName)
	{
		List<Item> itemList = repository.findItemListByRestaurant(restaurantName);
		if(itemList.size()!=0)
		{
			return itemList;
		}
		else
			throw new ItemListNotFoundException("ItemList Not Found");			
	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Item viewItem(String name) {
		// TODO Auto-generated method stub
		Item item = repository.findByItemName(name);
		if(item == null) {
			throw new ItemNotFoundException("Item Not Found");
		}else {
			return item;
		}
	}
}
