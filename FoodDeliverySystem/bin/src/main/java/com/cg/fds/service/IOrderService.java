package com.cg.fds.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;

@Service
@Transactional
public interface IOrderService {

	public OrderDetails addOrder(OrderDetails order);
	public OrderDetails removeOrder(OrderDetails order);
	public OrderDetails viewOrder(OrderDetails order);
	public OrderDetails viewOrder(int orderId);
	public List<OrderDetails> viewAllOrders(Restaurant resName);
	public List<OrderDetails> viewAllOrders(Customer customer);
	public OrderDetails updateOrder(OrderDetails order);
	public OrderDetails updateOrderStatus(OrderDetails order);
	public OrderDetails updateOrderDate(OrderDetails order);
	public OrderDetails updateOrderCart(OrderDetails order);
}
