package com.cg.fds.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.cg.fds.entities.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Integer>{

//	public Customer addCustomer(Customer customer);
	//public Customer updateCustomer(Customer customer);
	//public Customer deleteCustomer(Customer customer);
	public Customer findCustomerByCustomerId(int id);
	//public List<Customer> viewAllCustomer(String restaurantname); 
	
}
