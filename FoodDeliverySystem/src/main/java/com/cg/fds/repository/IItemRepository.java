package com.capgemini.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.fds.entities.Category;
import com.capgemini.fds.entities.Item;
import com.capgemini.fds.entities.ItemListEntity;
import com.capgemini.fds.entities.Restaurant;

public interface IItemRepository extends CrudRepository<Item, Integer>{

	//public Item addItem(Item item);
	public Item findByItemId(int id);
	public Item findByItemName(String name);
	//public Item updateItem(Item item);
	//public Item removeItem(String id);
	
	@Query("select r.itemList from Restaurant r where r.restaurantName = :name")
	public List<Item> findItemListByRestaurant(@Param("name")String restaurantName);
	public List<Item> findByCategory(Category cat);
	//public List<Item> findByItemName(String name);
	@Query("select i.restaurants from Item i where i.itemName = :name")
	public List<Restaurant> findRestaurantsByItemName(@Param("name") String name);
	
	public List<Item> findByCostBetween(@Param("minPrice")double minCost,@Param("maxPrice")double maxCost);
	
	@Query("select i from Item i where i.category = : category")
	public List<Item> searchByCategory(@Param("category") Category cat);
	public List<Item> findByItemNameStartsWith(String itemName);
	public List<Item> findByCostGreaterThanEqual(float cost);
	public List<Item> findByCategoryOrderByItemName(Category cat);
	public List<Item> findByCostBetween(float minCost, float maxCost);
}

