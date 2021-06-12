package com.capgemini.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fds.entities.Category;
import com.capgemini.fds.entities.Item;
import com.capgemini.fds.entities.Restaurant;
import com.capgemini.fds.service.ICategoryService;
import com.capgemini.fds.service.IItemService;
import com.capgemini.fds.service.IRestaurantService;


@RestController
public class ApplicationController {
	
@Autowired
	
	private IRestaurantService restaurantService;
@Autowired
	
	private IItemService service;

@Autowired
	private ICategoryService categoryService;
	@GetMapping(path = "/restaurants")
	
	   public ResponseEntity<List<Restaurant>> viewAllRestaurant()
	   {
		    List<Restaurant> restaurants= (List<Restaurant>) restaurantService.viewAllRestaurants();
	        if(restaurants.size()==0) {
			return new ResponseEntity("unAvailable Restaurant", HttpStatus.NOT_FOUND);
	   }
		return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
	 }

	
	@GetMapping(path="/restaurant/{name}")
	
	public ResponseEntity<List<Item>>viewAllItemsByNames(@PathVariable ("name")String name)
	{
		List<Item> items= (List<Item>)service.viewAllItemsByName(name);
		
		if(items==null) {
			return new ResponseEntity("Sorry!! Item was not found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	
	
	@GetMapping(path="/restaurant/{name}/{searchPrice}")
	
	public ResponseEntity<List<Item>> IItemList(@RequestParam("minPrice") float minPrice, @RequestParam("maxPrice") float maxPrice) 
	{
		List<Item> items = (List<Item>)service.searchByPriceBetween(minPrice,maxPrice);
		
		if(items==null)
		{
			return new ResponseEntity("Sorry!! Item was not found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Item>>(items,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(path="/restaurant/{name}/{category_name}")
	
	public ResponseEntity<List<Item>> viewByCategory(Category cat) 
	{
		List<Item> items = (List<Item>)service.searchByCategory(cat);
		
		if(items==null)
		{
			return new ResponseEntity("Category UnAvailable",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Item>>(items,HttpStatus.OK);
	}
	
	
	
	@GetMapping(path="/category")
	
	public ResponseEntity<List<Category>> viewAllCategory() 
	{
		List<Category> categories = (List<Category>)categoryService.viewAllCategory();
		
		if(categories.size()==0)
		{
			return new ResponseEntity("Category Not Found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	
	@GetMapping(path="/category/{name}")
	
	public ResponseEntity<Category>searchByCategoryName(@PathVariable("name")String name)
	{
		Category category= categoryService.searchByCategoryName(name);
		
		if(category==null) {
			return new ResponseEntity("Sorry!! Category was not found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
}
