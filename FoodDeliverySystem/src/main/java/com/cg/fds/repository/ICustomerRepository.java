package com.capgemini.fds.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.capgemini.fds.entities.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Integer>{

//	public Customer addCustomer(Customer customer);
	//public Customer updateCustomer(Customer customer);
	//public Customer deleteCustomer(Customer customer);
	public Customer findByCustomerId(int id);
	public Customer findByFirstName(String name);
	//public List<Customer> viewAllCustomer(String restaurantname); 
	
}