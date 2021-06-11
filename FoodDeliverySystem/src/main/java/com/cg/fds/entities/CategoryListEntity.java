/**
 * 
 */
package com.cg.fds.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author advai
 *
 */
@XmlRootElement(name = "categories")
public class CategoryListEntity {
	@JsonProperty("categories")	
	private List<Category> categoryList;

	@XmlElement(name = "restaurants")
	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> list) {
		this.categoryList = list;
	}
}
