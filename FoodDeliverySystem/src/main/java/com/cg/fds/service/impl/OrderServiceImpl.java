package com.capgemini.fds.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Customer;
import com.capgemini.fds.entities.OrderDetails;
import com.capgemini.fds.entities.Restaurant;
import com.capgemini.fds.exception.OrderNotFoundException;
import com.capgemini.fds.repository.IOrderRepository;
import com.capgemini.fds.service.IOrderService;

@Service
@Transactional
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
	public OrderDetails removeOrder(OrderDetails order, int orderId) {
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
	public OrderDetails viewOrder(int id) {
		// TODO Auto-generated method stub
		Optional<OrderDetails> isOrderAvailable = repository.findById(id);
		if(isOrderAvailable.isPresent()) {
			return isOrderAvailable.get();
		}else {
			throw new OrderNotFoundException("Order not found. ");
		}
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

	@Override
	public OrderDetails updateOrder(OrderDetails order, int orderId) {
		// TODO Auto-generated method stub
		Optional<OrderDetails> isOrderAvailable = repository.findById(orderId);
		if(isOrderAvailable.isPresent()) {
			repository.save(order);
			return order;
		}else {
			throw new OrderNotFoundException("Order not found. ");
		}
	}

}