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
	//public Item updateItem(Item item);
	//public Item removeItem(String id);
	//@Query("select i.itemList from ITEM i where i.itemId IN(select ri.itemId from RESTAURANT_ITEMS ri where ri.restaurantId IN(select r.restaurantId from RESTAURANT r where r.restaurantName = :name))")
	//public List<Item> findItemListByRestaurant(@Param("name") Restaurant res);
	public List<Item> findByCategory(Category cat);
	//public List<Item> findByItemName(String name);
	public List<Restaurant> findByItemName(String name);
	
}
