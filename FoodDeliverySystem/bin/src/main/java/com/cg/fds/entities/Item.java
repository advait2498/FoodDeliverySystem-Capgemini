package com.cg.fds.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Item {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	private String itemName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "category_id")
	private Category category;
	
	private int quantity;
	private double cost;
	
	@ManyToMany(mappedBy = "itemList", targetEntity = Restaurant.class, fetch=FetchType.LAZY)
	private List<Restaurant> restaurants = new ArrayList<>();
	
	//CONSTRUCTOR
	public Item() {
		
	}
	/**
	 * @param itemId
	 * @param itemName
	 * @param category
	 * @param quantity
	 * @param cost
	 * @param restaurants
	 */
	public Item(int itemId, String itemName, Category category, int quantity, double cost,
			List<Restaurant> restaurants) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.category = category;
		this.quantity = quantity;
		this.cost = cost;
		this.restaurants = restaurants;
	}
	
	//SETTER-GETTER
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the restaurants
	 */
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}
