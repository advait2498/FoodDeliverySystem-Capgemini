package com.capgemini.fds.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.validation.Valid;

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

import com.capgemini.fds.service.impl.CustomerCartImpl;
import com.capgemini.fds.service.impl.CustomerServiceImpl;
import com.capgemini.fds.service.impl.ItemServiceImpl;
import com.capgemini.fds.service.impl.OrderServiceImpl;
import com.capgemini.fds.entities.Customer;
import com.capgemini.fds.entities.FoodCart;
import com.capgemini.fds.entities.Item;
import com.capgemini.fds.entities.OrderDetails;

@RestController
@RequestMapping("/{id}")
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;
	
	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	CustomerCartImpl cartService;
	
	@Autowired
	ItemServiceImpl itemService;
	
	@GetMapping("/orders")
	public List<OrderDetails> getAllOrders(@PathVariable("id") int custId){
		Customer customer = customerService.viewCustomer(custId);
		return orderService.viewAllOrders(customer);
	}
	
	@GetMapping("/orders/{orderId}")
	public OrderDetails getOneOrders(@PathVariable("id") int custId, @PathVariable("orderId") int id){
		Customer customer = customerService.viewCustomer(custId);
		return orderService.viewOrder(id);
	}
	
	@GetMapping("/cart")
	public FoodCart getCurrentCart(@PathVariable("id") int custId) {
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		return cart;
	}
	
	@PutMapping("/cart/increaseQty")
    public ResponseEntity<FoodCart> increasingQuantity(@PathVariable("id") int custId, @RequestBody Item item,@RequestBody int quantity){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		FoodCart response=cartService.increaseQuantity(cart,item,quantity);      
	    return  ResponseEntity.ok().body(response);
	} 
     
	@PutMapping("/cart/decreaseQty")
	public ResponseEntity<FoodCart> reducingQuantity(@PathVariable("id") int custId, @RequestBody Item item,@RequestBody int quantity){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		FoodCart response=cartService.reduceQuantity(cart,item,quantity);   
	    return  ResponseEntity.ok().body(response);
	}
	    
	@PostMapping("/cart")
	public ResponseEntity<FoodCart> addOneItemToCart(@PathVariable("id") int custId, @RequestBody Item item){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		
	    FoodCart response = cartService.addItemToCart(cart,item);
	    if(response.equals(cart) && response.equals(item)) {
	        return ResponseEntity.ok().body(response);
	    }
	    return ResponseEntity.badRequest().body(response);
	}
	    
	@DeleteMapping("/cart/{itemId}")
	public ResponseEntity<FoodCart> deleteOneItem(@PathVariable("id") int custId, @RequestBody int itemId){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		
		Item item = itemService.viewItem(itemId);
	    FoodCart response=cartService.removeItem(cart,item);
	    
	    if(response.equals(cart) && response.equals(item)) {
	        return ResponseEntity.ok().body(response);
	    }
	    return ResponseEntity.badRequest().body(response);
	}
	
	@DeleteMapping("/cart")
	public ResponseEntity<FoodCart> clearACart(@PathVariable("id") int custId){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		
		FoodCart response=cartService.clearCart(cart);
	    return  ResponseEntity.ok().body(response);
	}

	//ORDER ENDPOINTS
	@PostMapping(consumes = "application/json")
	public OrderDetails addNewOrder(@Valid @RequestBody OrderDetails order){
	 	return orderService.addOrder(order);
	}
	 
	@PutMapping(path ="/{orderId}" ,consumes = "application/json" ,produces ="application/json")
	public OrderDetails updateOrder(@RequestBody OrderDetails order, @PathVariable int orderId) {
	 	
	 	return orderService.updateOrder(order, orderId);
	}
	 
	@DeleteMapping(path ="/{OrderId}" ,consumes = "application/json" ,produces ="application/json")
	public OrderDetails deleteOrder(@RequestBody OrderDetails order, @PathVariable int orderId) {
	 	
	 	return orderService.removeOrder(order, orderId);
	}
	 
	@ExceptionHandler(InputMismatchException.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	     List<String> details = new ArrayList<>();
	     details.add(ex.getLocalizedMessage());
	     ErrorResponse error = new ErrorResponse("Server Error From controller", details);
	     return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	 }

	@PutMapping("/profile-update")
	public ResponseEntity<Customer> updateProfile(@PathVariable int custId){
		Customer response = customerService.updateCustomer(custId);
		return  ResponseEntity.accepted().body(response);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<Customer> getOneCustomer(@PathVariable int customerId){
			Customer customer = customerService.viewCustomer(customerId);
			if(customer==null) {
				return ResponseEntity.badRequest().body(customer);
			}
		return ResponseEntity.ok().body(customer);
	}
}
