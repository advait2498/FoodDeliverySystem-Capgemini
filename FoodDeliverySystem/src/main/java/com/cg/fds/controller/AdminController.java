package com.capgemini.fds.controller;
import com.capgemini.fds.entities.*;
import com.capgemini.fds.service.ICustomerService;
import com.capgemini.fds.service.IItemService;
import com.capgemini.fds.service.IOrderService;
import com.capgemini.fds.service.IRestaurantService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IItemService itemService;
	
	@GetMapping("/restaurants/{itemName}")
	public RestaurantListEntity getRestaurantByItemName(@PathVariable("itemName") String itemName){
		RestaurantListEntity restaurantListEntity =new RestaurantListEntity();
		
		restaurantListEntity.setRestaurantList(restaurantService.viewRestaurantByItemName(itemName));
		return restaurantListEntity;
	}
	
	
	@GetMapping("/restaurants/{location}")
	public RestaurantListEntity getNearByRestaurant(@PathVariable("location") String location){
		RestaurantListEntity restaurantListEntity =new RestaurantListEntity();
		restaurantListEntity.setRestaurantList(restaurantService.viewNearByRestaurant(location));
		return restaurantListEntity;
	}	
	
	@GetMapping("/restaurants")
	public RestaurantListEntity getAllRestaurants(){
		RestaurantListEntity restaurantListEntity =new RestaurantListEntity();		
		restaurantListEntity.setRestaurantList(restaurantService.viewAllRestaurants());
		return restaurantListEntity;
	}	

	@GetMapping("/restaurant/{restaurantName}")
	public ResponseEntity<Restaurant> getOneRestaurant(@PathVariable String restaurantName){
		Restaurant response = restaurantService.viewRestaurant(restaurantName);
		if(response==null) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/restaurant/{restaurantName}")
	public ResponseEntity<Restaurant> updateOneRestaurant(@PathVariable Restaurant restaurant){
		Restaurant response = restaurantService.updateRestaurant(restaurant.getRestaurantId(),restaurant);
		return  ResponseEntity.accepted().body(response);
	}
	
	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> addOneRestaurant(@RequestBody Restaurant restaurant){
		Restaurant response = restaurantService.addRestaurant(restaurant);
		if(response.equals(restaurant)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
	
	@DeleteMapping("/restaurant/{restaurantName}")
 	public ResponseEntity<Restaurant> deleteOneRestaurant(@PathVariable Restaurant restaurant){
	 	Restaurant response=restaurantService.removeRestaurant(restaurant.getRestaurantId());
        if(response.equals(restaurant)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	
	
	// ITEMS ENDPOINTS
	
	@GetMapping("/restaurants/{restaurantName}/items/{itemName}")
	public ResponseEntity<Item> viewItemByRestaurantName(@PathVariable("restaurantName") String restaurantName, @RequestBody String item){
		Item response = itemService.viewItems(item);
		if(response.getItemName().equals(item))
		{
			return ResponseEntity.ok().body(response);
		}
		return ResponseEntity.badRequest().body(response);
		
	}
	
	@PostMapping("/restaurants/{restaurantName}/")
	public ResponseEntity<Item> addItemsBulkByRestaurantName(@PathVariable("restaurantName") String restaurantName, @RequestBody Item item){
		Item response = itemService.addItem(item);
		if(response.equals(item)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	@PutMapping("/restaurants/{restaurantName}/items/{itemName}")
	public ResponseEntity<Item> addItemByRestaurantName(@PathVariable("restaurantName") String restaurantName, @RequestBody String itemName){
		Item response = itemService.updateItem(itemName);
		if(response.getItemName().equals(itemName)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	
	@DeleteMapping("/restaurants/{restaurantName}/items/{itemName}")
	public ResponseEntity<Item> updateItemByRestaurantName(@PathVariable("restaurantName") String restaurantName, @RequestBody String itemName){
		Item item = itemService.viewItems(itemName);
		Item response = itemService.removeItem(item.getItemId(), item);
		if(response.getItemName().equals(itemName)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	
	@GetMapping("/restaurants/{restaurantName}/itemList")
	public ItemListEntity getAllItems(@PathVariable("restaurantName") String restaurantName)
	{
		ItemListEntity itemListEntity = new ItemListEntity();
		itemListEntity.setItemList(itemService.findItemListByRestaurant(restaurantName));
		return itemListEntity;
	}	
	
	//CUSTOMER ENDPOINTS
	
	@GetMapping("/customers")
	public CustomerListEntity getAllCustomers(){
		CustomerListEntity customerListEntity =new CustomerListEntity();
		customerListEntity.setCustomerList(customerService.viewAllCustomer());
		return customerListEntity;
	}	
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getOneCustomer(@PathVariable int customerId){
		Customer customer = customerService.viewCustomer(customerId);
		if(customer==null) {
			return ResponseEntity.badRequest().body(customer);
		}
		 return ResponseEntity.ok().body(customer);
	}
	
	@PutMapping("/customers/{customerId}")
	public ResponseEntity<Customer> updateOneCustomer(@PathVariable Customer customer){
		Customer response = customerService.updateCustomer(customer.getCustomerId());
		return  ResponseEntity.accepted().body(response);
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addOneCustomer(@RequestBody Customer customer){
		Customer response = customerService.addCustomer(customer);
		if(response.equals(customer)) {
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body(response);
    }
	
	 @DeleteMapping("/customers/{customerId}")
	 public ResponseEntity<Customer> deleteOneCustomer(@PathVariable Customer customer){
		 Customer response=customerService.removeCustomer(customer.getCustomerId());
	     if(response.equals(customer)) {
	    	 return ResponseEntity.ok().body(response);
	     }
	     return ResponseEntity.badRequest().body(response);
	 }
	 
	 @ExceptionHandler(InputMismatchException.class)
	 public final ResponseEntity<Object> handleAllExceptionsEntity(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error From controller", details);
        return  ResponseEntity.badRequest().body(error);
	 }
	 	 
	 @GetMapping(path = "/{orderbyId}",produces = {"application/json","application/xml"})
	 public ResponseEntity<OrderDetails> findOrderById(@PathVariable int orderDetailsId) {
	 	
	 	return new ResponseEntity<>(orderService.viewOrder(orderDetailsId),HttpStatus.OK);
	 }
	
}
