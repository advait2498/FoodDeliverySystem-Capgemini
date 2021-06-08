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
	private int cartId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items;
	
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
	public FoodCart(int cartId, Customer customer, List<Item> itemList) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.items = itemList;
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
		return items;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.items = itemList;
	}

	/**
	 * @return the cartId
	 */
	public int getCartId() {
		return cartId;
	}
	
}
