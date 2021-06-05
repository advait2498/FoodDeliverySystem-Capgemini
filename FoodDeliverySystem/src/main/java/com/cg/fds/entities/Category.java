package com.cg.fds.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private String catId;
	
	private String categoryName;

	//CONSTRUCTOR
	/**
	 * @param catId
	 * @param categoryName
	 */
	public Category(String catId, String categoryName) {
		super();
		this.catId = catId;
		this.categoryName = categoryName;
	}

	//SETTER-GETTER
	/**
	 * @return the catId
	 */
	public String getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
