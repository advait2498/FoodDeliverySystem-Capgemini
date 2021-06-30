package com.cg.fds.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.entities.Restaurant;

public interface IOrderRepository extends CrudRepository<OrderDetails, Integer>{

	//public OrderDetails addOrder(OrderDetails order);
	@Modifying
	@Query("update OrderDetails o set o.orderStatus = ?1 where o.orderId = ?2")
	public OrderDetails updateOrderStatus(String status, int orderId);
	
	@Modifying
	@Query("update OrderDetails o set o.cart = ?1 where o.orderId = ?2")
	public OrderDetails updateOrderCart(FoodCart cart, int orderId);

	@Modifying
	@Query("update OrderDetails o set o.orderDate = ?1 where o.orderId = ?2")
	public OrderDetails updateOrderDate(LocalDateTime date, int orderId);
	

	//public OrderDetails updateOrder(OrderDetails order, int orderId);
	
	@Modifying
	@Query("delete from OrderDetails o where o.orderId = :id")
	public OrderDetails removeOrder(@Param("id")int orderId);

	public OrderDetails findByOrderId(int orderId);
	
	//public List<OrderDetails> viewAllOrders(Restaurant resName);
	//public List<OrderDetails> viewAllOrders(Customer customer);
	
}
