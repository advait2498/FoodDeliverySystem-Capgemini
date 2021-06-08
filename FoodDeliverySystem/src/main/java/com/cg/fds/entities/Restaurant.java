package com.cg.fds.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Restaurant {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int restaurantId;
	private String restaurantName;
	
	@OneToOne
	@JoinColumn(name="restaurant_address")
	private Address address;
	
	@ManyToMany
	@JoinTable(
			name = "restaurant_items", 
			joinColumns= @JoinColumn(name = "restaurantId"),
			inverseJoinColumns = @JoinColumn(name = "itemId"))
	private List<Item> itemList = new ArrayList<Item>();
	
	private String managerName;
	private String contactNumber;
	
	//CONSTRUCTORS
	public Restaurant() {
		
	}
	/**
	 * @param restaurantId
	 * @param restaurantName
	 * @param address
	 * @param itemList
	 * @param managerName
	 * @param contactNumber
	 */
	public Restaurant(int restaurantId, String restaurantName, Address address, List<Item> itemList,
			String managerName, String contactNumber) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.address = address;
		this.itemList = itemList;
		this.managerName = managerName;
		this.contactNumber = contactNumber;
	}
	//SETTER-GETTER

	/**
	 * @return the restaurantId
	 */
	public int getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}

	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}	
}
