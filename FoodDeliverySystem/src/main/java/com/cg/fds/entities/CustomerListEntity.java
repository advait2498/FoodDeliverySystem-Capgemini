package com.capgemini.fds.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "customers")
public class CustomerListEntity {

	@JsonProperty("customers")	
	private List<Customer> customerList;

	@XmlElement(name = "customers")
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> list) {
		this.customerList = list;
	}
}
