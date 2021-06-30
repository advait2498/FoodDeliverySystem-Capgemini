package com.cg.fds.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Bill {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int billId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="order_id")
	private OrderDetails order;
	
	private int totalItem;
	private double totalCost;
	LocalDateTime billDate;
	
	//CONSTRUCTOR
	/**
	 * @param billId
	 * @param order
	 * @param totalItem
	 * @param totalCost
	 * @param billDate
	 */
	public Bill(int billId, OrderDetails order, int totalItem, double totalCost, LocalDateTime billDate) {
		super();
		this.billId = billId;
		this.order = order;
		this.totalItem = totalItem;
		this.totalCost = totalCost;
		this.billDate = billDate;
	}
	
	//SETTER-GETTER
	/**
	 * @return the billId
	 */
	public int getBillId() {
		return billId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(int billId) {
		this.billId = billId;
	}
	/**
	 * @return the order
	 */
	public OrderDetails getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(OrderDetails order) {
		this.order = order;
	}
	/**
	 * @return the totalItem
	 */
	public int getTotalItem() {
		return totalItem;
	}
	/**
	 * @param totalItem the totalItem to set
	 */
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * @return the billDate
	 */
	public LocalDateTime getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}
}
