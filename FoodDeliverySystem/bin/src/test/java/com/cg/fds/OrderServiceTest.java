package com.cg.fds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.IOrderRepository;

import com.cg.fds.service.impl.OrderServiceImpl;

@SpringBootTest
public class OrderServiceTest {
	
	@InjectMocks
	OrderServiceImpl orderService;
	@Mock
	IOrderRepository orderRepository;
	
	
	
	@Test
	public void addOrder()
	{
		 
		OrderDetails expOrder = new OrderDetails(897, null, null, "on the way");
		OrderDetails expOrder1 = new OrderDetails(859, null, null, "started");
		Mockito.when(orderRepository.save(expOrder)).thenReturn(expOrder); 
		OrderDetails actOrder=orderService.addOrder(expOrder);
		assertEquals(expOrder.getOrderId(),actOrder.getOrderId());
		Mockito.when(orderRepository.save(expOrder1)).thenReturn(expOrder1); 
		OrderDetails actOrder1=orderService.addOrder(expOrder1);
		assertEquals(expOrder1.getOrderId(),actOrder1.getOrderId());
	}
	@Test
	public void updateOrder()
	{
		OrderDetails expOrder = new OrderDetails(859, null, null, "started");
		Mockito.when(orderRepository.findById(859)).thenReturn(Optional.of(expOrder));
		OrderDetails actOrder=orderService.updateOrderCart(expOrder);
		assertEquals(expOrder.getOrderId(),actOrder.getOrderId());
		verify(orderRepository,times(1)).save(expOrder); 
	}
	@Test
	public void removeOrder()
	{
		OrderDetails expOrder = new OrderDetails(256, null, null, "half way");
		orderRepository.delete(expOrder);
		verify(orderRepository,times(1)).delete(expOrder);;
		
	}
	@Test
	public void viewOrder()
	{
		OrderDetails expOrder = new OrderDetails(263, null, null, "arrived");
		Mockito.when(orderRepository.findById(263)).thenReturn(Optional.of(expOrder));
		OrderDetails actOrder=orderService.viewOrder(263);
		assertEquals(expOrder.getOrderId(),actOrder.getOrderId());
		verify(orderRepository,times(1)).findById(263);
	} 
	@Test
	public void viewAllOrdersByCustomer() {
		
		
		Customer customer1 = new Customer("chandhana", "krishna", "f", "22", "46326523", "ckr@gmail.com",null);
		Customer customer2 = new Customer("shivani", "priya", "m", "26", "5896523", "ckr@gmail.com", null);
		Customer customer3 = new Customer("shraddha", "mohan", "f", "28", "85754523", "ckr@gmail.com", null);
	    List<OrderDetails> order_details = new ArrayList<>();
	    
	    List<Restaurant> r1 = new ArrayList<Restaurant>();
	    List<Restaurant> r2 = new ArrayList<Restaurant>();
	    List<Restaurant> r3 = new ArrayList<Restaurant>();
	    List<Restaurant> r4 = new ArrayList<Restaurant>();
	    List<Restaurant> r5 = new ArrayList<Restaurant>();
	    OrderDetails Order1 = new OrderDetails(101, null, null, "arrived");
		OrderDetails Order2 = new OrderDetails(102, null, null, "delivered");
		OrderDetails Order3 = new OrderDetails(103, null, null, "order picked");
		OrderDetails Order4 = new OrderDetails(104, null, null, "half way");
		OrderDetails Order5 = new OrderDetails(105, null, null, "arrived");
		order_details.add(Order1);
		order_details.add(Order2);
		order_details.add(Order3);
		order_details.add(Order4);
		order_details.add(Order5);
	    Mockito.when(orderRepository.findAll()).thenReturn(order_details);
	    List<OrderDetails> list = orderService.viewAllOrders(customer1);
	    assertEquals(order_details.size(),list.size());
	    assertEquals(101,Order1.getOrderId());
	    assertEquals(null,Order1.getCart());
	    assertEquals(null,Order1.getOrderDate());
	    assertEquals("half way",Order1.getOrderStatus());
	   
	  
	
	}
		
}

