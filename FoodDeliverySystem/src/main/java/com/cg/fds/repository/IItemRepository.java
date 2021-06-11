package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;

public interface IItemRepository extends CrudRepository<Item, Integer>{

	//public Item addItem(Item item);
	public Item findByItemId(int id);
	public Item findByItemName(String name);
	//public Item updateItem(Item item);
	//public Item removeItem(String id);
	
	@Query("select r.itemList from Restaurant r where r.restaurantName = :name")
	public List<Item> findItemListByRestaurant(@Param("name") Restaurant res);
	public List<Item> findByCategory(Category cat);
	//public List<Item> findByItemName(String name);
	@Query("select i.restaurantList from Item i where i.itemName = :name")
	public List<Restaurant> findRestaurantsByItemName(@Param("name") String name);
	
	public List<Item> findByCostBetween(@Param("minPrice")double minCost,@Param("maxPrice")double maxCost);
	
	@Query("select i from Item i where i.category = : category")
	public List<Item> searchByCategory(@Param("category") Category cat);
	
}
