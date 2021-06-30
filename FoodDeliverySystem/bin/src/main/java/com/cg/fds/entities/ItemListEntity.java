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
@XmlRootElement(name = "items")
public class ItemListEntity {
	@JsonProperty("items")	
	private List<Item> itemList;

	@XmlElement(name = "items")
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> list) {
		this.itemList = list;
	}
}
