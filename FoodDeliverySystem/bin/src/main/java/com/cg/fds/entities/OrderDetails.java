package com.cg.fds.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private LocalDateTime orderDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private FoodCart cart;
	private String orderStatus;
	
	//CONSTRUCTOR
	public OrderDetails() {
		
	}
	/**
	 * @param orderId
	 * @param orderDate
	 * @param cart
	 * @param orderStatus
	 */
	public OrderDetails(int orderId, LocalDateTime orderDate, FoodCart cart, String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.cart = cart;
		this.orderStatus = orderStatus;
	}
	
	//SETTER-GETTER
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderDate
	 */
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the cart
	 */
	public FoodCart getCart() {
		return cart;
	}
	/**
	 * @param cart the cart to set
	 */
	public void setCart(FoodCart cart) {
		this.cart = cart;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
