package com.capgemini.fds.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Customer;

@Service
@Transactional
public interface ICustomerService {

	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(int customerId);
	public Customer removeCustomer(int customerId);
	public Customer viewCustomer(int customerId);
	//public List<Customer> viewAllCustomer(String restaurantname);
	public List<Customer> viewAllCustomer(); 
}
