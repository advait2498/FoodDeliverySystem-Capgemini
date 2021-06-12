package com.capgemini.fds.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Category;
import com.capgemini.fds.entities.Item;
import com.capgemini.fds.entities.ItemListEntity;
import com.capgemini.fds.entities.Restaurant;

@Service
@Transactional
public interface IItemService {

	public Item addItem(Item item);
	public Item viewItem(int id);
	public Item viewItems(String name);
	public Item updateItem(int id, Item item);
	public Item updateItem(String itemName);
	public Item removeItem(int id, Item item);
	public List<Item> viewAllItems(Restaurant res);
	public List<Item> viewAllItems(Category cat);
	public List<Item> viewAllItemsByName(String name);
	public List<Item> searchByPriceBetween(double minPrice,double maxPrice);
	public List<Item> searchByCategory(Category cat);
	public List<Item> findByItemNameStartsWith(String itemName);
	public List<Item> findByCostGreaterThanEqual(float cost);
	public List<Item> findByCategoryOrderByItemName(Category cat);
	public List<Item> findByCostBetween(float minCost, float maxCost);
	public List<Item> findItemListByRestaurant(String restaurantName);
}