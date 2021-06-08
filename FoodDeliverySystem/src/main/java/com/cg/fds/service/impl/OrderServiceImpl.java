/**
 * 
 */
package com.cg.fds.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exception.OrderNotFoundException;
import com.cg.fds.repository.IOrderRepository;
import com.cg.fds.service.IOrderService;

/**
 * @author advai
 *
 */
public class OrderServiceImpl implements IOrderService{

	@Autowired
	IOrderRepository repository;
	
	@Override
	public OrderDetails addOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return repository.save(order);
	}

	@Override
	public OrderDetails updateOrderStatus(OrderDetails order) {
		// TODO Auto-generated method stub
		return repository.updateOrderStatus(order.getOrderStatus(), order.getOrderId());
	}
	
	@Override
	public OrderDetails updateOrderDate(OrderDetails order) {
		// TODO Auto-generated method stub
		return repository.updateOrderStatus(order.getOrderStatus(), order.getOrderId());
	}
	
	@Override
	public OrderDetails updateOrderCart(OrderDetails order) {
		// TODO Auto-generated method stub
		return repository.updateOrderCart(order.getCart(), order.getOrderId());
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		Optional<OrderDetails> isOrderAvailable = repository.findById(order.getOrderId());
		if(isOrderAvailable.isPresent()) {
			repository.delete(order);
			return isOrderAvailable.get();
		}else {
			throw new OrderNotFoundException("Order not found. ");
		}
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return repository.findByOrderId(order.getOrderId());
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant resName) {
		// TODO Auto-generated method stub
		return (List<OrderDetails>) repository.findAll();
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		// TODO Auto-generated method stub
		return (List<OrderDetails>) repository.findAll();
	}

}
