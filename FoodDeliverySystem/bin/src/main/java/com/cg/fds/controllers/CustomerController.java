/**
 * 
 */
package com.cg.fds.controllers;

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

import com.cg.fds.service.ICartService;
import com.cg.fds.service.ICustomerService;
import com.cg.fds.service.IItemService;
import com.cg.fds.service.IOrderService;
import com.cg.fds.service.impl.CustomerCartImpl;
import com.cg.fds.service.impl.CustomerServiceImpl;
import com.cg.fds.service.impl.ItemServiceImpl;
import com.cg.fds.service.impl.OrderServiceImpl;
import com.cg.fds.entities.Address;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.OrderDetails;

/**
 * @author advai
 *
 */
@RestController
@RequestMapping("api")
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	ICartService cartService;
	
	@Autowired
	IItemService itemService;
	
	@GetMapping("{id}/orders")
	public List<OrderDetails> getAllOrders(@PathVariable("id") int custId){
		Customer customer = customerService.viewCustomer(custId);
		return orderService.viewAllOrders(customer);
	}
	
	@GetMapping("{id}/orders/{orderId}")
	public OrderDetails getOneOrders(@PathVariable("id") int custId, @PathVariable("orderId") int id){
		Customer customer = customerService.viewCustomer(custId);
		return orderService.viewOrder(id);
	}
	
	@GetMapping("customer/{id}/cart")
	public FoodCart getCurrentCart(@PathVariable("id") int custId) {
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		return cart;
	}
	
	@PutMapping("customer/{id}/cart/increaseQty")
    public ResponseEntity<FoodCart> increasingQuantity(@PathVariable("id") int custId, @RequestBody Item item,@RequestBody int quantity){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		FoodCart response=cartService.increaseQuantity(cart,item,quantity);      
	    return  ResponseEntity.ok().body(response);
	} 
     
	@PutMapping("customer/{id}/cart/decreaseQty")
	public ResponseEntity<FoodCart> reducingQuantity(@PathVariable("id") int custId, @RequestBody Item item,@RequestBody int quantity){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		FoodCart response=cartService.reduceQuantity(cart,item,quantity);   
	    return  ResponseEntity.ok().body(response);
	}
	    
	@PostMapping("customer/{id}/cart")
	public ResponseEntity<FoodCart> addOneItemToCart(@PathVariable("id") int custId, @RequestBody Item item){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		
	    FoodCart response = cartService.addItemToCart(cart,item);
	    if(response.equals(cart) && response.equals(item)) {
	        return ResponseEntity.ok().body(response);
	    }
	    return ResponseEntity.badRequest().body(response);
	}
	    
	@DeleteMapping("customer/{id}/cart/{itemId}")
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
	
	@DeleteMapping("customer/{id}/cart")
	public ResponseEntity<FoodCart> clearACart(@PathVariable("id") int custId){
		Customer customer = customerService.viewCustomer(custId);
		FoodCart cart = cartService.viewCart(customer);
		
		FoodCart response=cartService.clearCart(cart);
	    return  ResponseEntity.ok().body(response);
	}

	//ORDER ENDPOINTS
	@PostMapping(path = "customer/{id}/orders",consumes = "application/json")
	public OrderDetails addNewOrder(@Valid @RequestBody OrderDetails order){
	 	return orderService.addOrder(order);
	}
	 
	@PutMapping(path ="customer/{id}/orders/{orderId}" ,consumes = "application/json" ,produces ="application/json")
	public OrderDetails updateOrder(@RequestBody OrderDetails order, @PathVariable int orderId) {
	 	
	 	return orderService.updateOrder(order);
	}
	 
	@DeleteMapping(path ="customer/{id}/orders/{orderId}" ,consumes = "application/json" ,produces ="application/json")
	public OrderDetails deleteOrder(@RequestBody OrderDetails order, @PathVariable int orderId) {
	 	
	 	return orderService.removeOrder(order);
	}
	 
	@ExceptionHandler(InputMismatchException.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	     List<String> details = new ArrayList<>();
	     details.add(ex.getLocalizedMessage());
	     ErrorResponse error = new ErrorResponse("Server Error From controller", details);
	     return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	 }

	@PutMapping("customer/{id}/profile-update")
	public ResponseEntity<Customer> updateProfile(@PathVariable("id") int customerId, @RequestBody Address address){
		Customer customer = customerService.viewCustomer(customerId);
		customer.setAddress(address);
		Customer response = customerService.updateCustomer(customer);
		return  ResponseEntity.accepted().body(response);
	}
	
	@GetMapping("profile/{id}")
	public ResponseEntity<Customer> getOneCustomer(@PathVariable("id") int customerId){
			Customer customer = customerService.viewCustomer(customerId);
			if(customer==null) {
				return ResponseEntity.badRequest().body(customer);
			}
		return ResponseEntity.ok().body(customer);
	}
}
