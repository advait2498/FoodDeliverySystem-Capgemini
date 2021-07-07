/**
 * 
 */
package com.cg.fds.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.ItemListEntity;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.service.IItemService;
import com.cg.fds.service.IRestaurantService;

/**
 * @author advai
 *
 */

@RestController
public class RestaurantController {
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private IRestaurantService restaurantService;
	@PutMapping("/restaurant/{restaurantName}")
	public ResponseEntity<Restaurant> updateOneRestaurant(@RequestBody Restaurant restaurant){
		Restaurant response = restaurantService.updateRestaurant(restaurant);
		return  ResponseEntity.accepted().body(response);
	}
	
	@PutMapping("/restaurant/{restaurantName}/address")
	public ResponseEntity<Restaurant> updateOneRestaurantAddress(@PathVariable String restaurantName,@RequestBody Address address){
		Restaurant restaurant = restaurantService.viewRestaurant(restaurantName);
		restaurant.setAddress(address);
		Restaurant response = restaurantService.updateRestaurant(restaurant);
		return  ResponseEntity.accepted().body(response);
	}
	
	@GetMapping("/restaurants/{restaurantName}/items")
	public ItemListEntity viewItemByRestaurantName(@PathVariable("restaurantName") String restaurantName){
		Restaurant restaurant = restaurantService.viewRestaurant(restaurantName);
		ItemListEntity items = new ItemListEntity();
		items.setItemList(itemService.viewAllItems(restaurant));
		return items;
	}
	
	@PostMapping("/restaurants/{restaurantName}/items")
	public ResponseEntity<Item> addItemsBulkByRestaurantName(@PathVariable("restaurantName") String restaurantName, @RequestBody Item item){
		Item response = itemService.addItem(item);
		Restaurant restaurant = restaurantService.viewRestaurant(restaurantName);
		List<Item> items = restaurant.getItemList();
		items.add(response);
		restaurant.setItemList(items);
		if(response.equals(item)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	@PutMapping("/restaurants/{restaurantName}/items/{itemName}")
	public ResponseEntity<Item> updateItemByRestaurantName(@PathVariable("restaurantName") String restaurantName, @PathVariable String itemName){
		Item response = itemService.updateItem(itemName);
		if(response.getItemName().equals(itemName)) {
			Restaurant restaurant = restaurantService.viewRestaurant(restaurantName);
			List<Item> itemList = restaurant.getItemList();
			if(itemList.contains(itemService.viewItem(itemName))) {
				itemList.remove(itemService.viewItem(itemName));
				itemList.add(response);
			}
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	
	@DeleteMapping("/restaurants/{restaurantName}/items/{itemName}")
	public ResponseEntity<Item> deleteItemByRestaurantName(@PathVariable("restaurantName") String restaurantName, @PathVariable String itemName){
		Item item = itemService.viewItem(itemName);
		Item response = itemService.removeItem(item.getItemId(), item);
		Restaurant restaurant = restaurantService.viewRestaurant(restaurantName);
		List<Item> itemList = restaurant.getItemList();
		if(itemList.contains(item)) {
			itemList.remove(item);
		}
		if(response.getItemName().equals(itemName)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
}
