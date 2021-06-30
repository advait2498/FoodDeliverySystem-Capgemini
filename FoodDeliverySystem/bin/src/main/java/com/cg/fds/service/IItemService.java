package com.cg.fds.service;

import java.util.List;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

public interface IItemService {

	public Item addItem(Item item);
	public Item viewItem(int id);
	public Item viewItem(String name);
	public Item viewItems(String name);
	public Item updateItem(int id, Item item);
	public Item updateItem(String itemName);
	public void removeItem(int id);
	public Item removeItem(int id,Item item);
	public List<Item> viewAllItems(Restaurant res);
	public List<Item> viewAllItems(Category cat);
	public List<Item> viewAllItemsByName(String name);
	public List<Item> findItemListByRestaurant(String name);
	public List<Item> searchByPriceBetween(double minPrice, double maxPrice);
	public List<Item> searchByCategory(Category cat);
	public List<Item> findByItemNameStartsWith(String item);
	public List<Item> findByCostGreaterThanEqual(float cost);
	public List<Item> findByCategoryOrderByItemName(Category cat);
	public List<Item> findByCostBetween(float minCost, float maxCost);
}
