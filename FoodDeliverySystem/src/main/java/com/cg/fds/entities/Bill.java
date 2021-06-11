package com.cg.fds.entities;

import java.time.LocalDateTime;

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
	@OneToOne
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + billId;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + totalItem;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (billDate == null) {
			if (other.billDate != null)
				return false;
		} else if (!billDate.equals(other.billDate))
			return false;
		if (billId != other.billId)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		if (totalItem != other.totalItem)
			return false;
		return true;
	}
	
	
}
