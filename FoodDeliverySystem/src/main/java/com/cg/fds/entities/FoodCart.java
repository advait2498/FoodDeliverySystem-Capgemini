package com.cg.fds.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FoodCart {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private String cartId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> itemList;
	
	//CONSTRUCTORS
	//default constructor
	public FoodCart() {
		
	}
	//parameterized constructor

	/**
	 * @param cartId
	 * @param customer
	 * @param itemList
	 */
	public FoodCart(String cartId, Customer customer, List<Item> itemList) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.itemList = itemList;
	}
	//SETTER-GETTER

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the itemList
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the cartId
	 */
	public String getCartId() {
		return cartId;
	}
	
}
