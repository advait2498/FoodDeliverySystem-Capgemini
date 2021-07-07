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
@XmlRootElement(name = "restaurants")
public class RestaurantListEntity {
	@JsonProperty("restaurants")	
	private List<Restaurant> restaurantList;

	@XmlElement(name = "restaurants")
	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> list) {
		this.restaurantList = list;
	}
}
