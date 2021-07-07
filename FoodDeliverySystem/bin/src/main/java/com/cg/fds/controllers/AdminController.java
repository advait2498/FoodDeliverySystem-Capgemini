package com.cg.fds.controllers;
import com.cg.fds.entities.*;
import com.cg.fds.service.ICustomerService;
import com.cg.fds.service.IItemService;
import com.cg.fds.service.IOrderService;
import com.cg.fds.service.IRestaurantService;

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
	 	Restaurant response=restaurantService.removeRestaurant(restaurant);
        if(response.equals(restaurant)) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
	}
	// ITEMS ENDPOINTS
	
	
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
	public ResponseEntity<Customer> updateOneCustomer(@RequestBody Customer customer){
		Customer response = customerService.updateCustomer(customer);
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
	 public ResponseEntity<Customer> deleteOneCustomer(@RequestBody Customer customer){
		 Customer response=customerService.removeCustomer(customer);
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
