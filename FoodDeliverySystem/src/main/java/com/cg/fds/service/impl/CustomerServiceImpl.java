package com.cg.fds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Customer;
import com.cg.fds.exception.CustomerNotFoundException;
import com.cg.fds.exception.CustomerNotProvidedException;
import com.cg.fds.repository.ICustomerRepository;
import com.cg.fds.service.ICustomerService;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository repository;
	
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customer_saved = repository.save(customer);
		return customer_saved;
	}

	@Override
	public Customer updateCustomer(int customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> isCustomerAvailable = repository.findById(customerId);
		if(isCustomerAvailable.isPresent()) {
			Customer customerToUpdate = isCustomerAvailable.get();
			Customer updatedCustomer = repository.save(customerToUpdate);
			return updatedCustomer;
		}
		else {
			throw new CustomerNotFoundException("No customer found to update... Please provide valid argument.");
		}
	}

	@Override
	public Customer removeCustomer(int customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> isCustomerAvailable = repository.findById(customerId);
		if(isCustomerAvailable.isPresent()) {
			Customer customer = isCustomerAvailable.get();
			repository.delete(customer);
			return customer;
		}else {
			throw new CustomerNotProvidedException("No customer found in argument... Please provide valid argument.");
		}
	}

	@Override
	public Customer viewCustomer(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> isCustomerFound = repository.findById(id);
		if(isCustomerFound.isPresent()) {
			return isCustomerFound.get();
		}
		else
			throw new CustomerNotFoundException("No customer found to show... Please provide valid argument.");
	}

	@Override
	public List<Customer> viewAllCustomer() {
		// TODO Auto-generated method stub
		return (List<Customer>) repository.findAll();
	}

}
