package com.cg.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Customer;
import com.cg.fds.repository.ICustomerRepository;
import com.cg.fds.service.impl.CustomerServiceImpl;

@SpringBootTest
class CustomerTest {
	
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	@Mock
	private ICustomerRepository customerRepository;
	

	@Test
	public void addCustomertest() 
	{
		Address address = new Address(101, "Madhav Towers", "Gachibowli", "28-11", "Hyderabad", "Telangana", "India", "500026");
		Customer expCust = new Customer("Advait","Kulkarni","Male","24","8840319912","advait.k@cg.com",address);
		Mockito.when(customerRepository.save(expCust)).thenReturn(expCust);
		
		Customer actCust = customerServiceImpl.addCustomer(expCust);
		assertEquals(expCust.getFirstName(), actCust.getFirstName());
	
	}
	
	@Test
	public void updateCustomerTest() throws Exception
	{
		Address address = new Address(101, "Madhav Towers", "Gachibowli", "28-11", "Hyderabad", "Telangana", "India", "500026");
		Customer expCust = new Customer("Advait","Kulkarni","Male","24","8840319912","advait.k@cg.com",address);
		
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(expCust));
		
		Customer actCust = customerServiceImpl.updateCustomer(expCust);
		assertEquals(expCust.getFirstName(), actCust.getFirstName());
		verify(customerRepository,times(1)).findById(1);
	}
	
	@Test
	public void removeCustomerTest() throws Exception
	{
		Address address = new Address(101, "Madhav Towers", "Gachibowli", "28-11", "Hyderabad", "Telangana", "India", "500026");
		Customer expCust = new Customer("Advait","Kulkarni","Male","24","8840319912","advait.k@cg.com",address);		
		customerRepository.delete(expCust);
	}
	
	@Test
	public void viewCustomerTest()
	{
		Address address = new Address(101, "Madhav Towers", "Gachibowli", "28-11", "Hyderabad", "Telangana", "India", "500026");
		Customer expCust = new Customer("Advait","Kulkarni","Male","24","8840319912","advait.k@cg.com",address);
		
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(expCust));
		
		Customer actCust = customerServiceImpl.viewCustomer(1);
		assertEquals(expCust.getFirstName(), actCust.getFirstName());
	}
	
	@Test
	public void viewAllCustomerTest()
	{
		Address address = new Address(101, "Madhav Towers", "Gachibowli", "28-11", "Hyderabad", "Telangana", "India", "500026");
		//Customer expCust = new Customer("Surya", "Venkat", "M","28", "7654823591", address, "venkat@xyz.com");
		Customer expCust = new Customer("Advait","Kulkarni","Male","24","8840319912","advait.k@cg.com",address);
		List<Customer> custList = new ArrayList<>();
		custList.add(expCust);
		Mockito.when(customerRepository.findAll()).thenReturn(custList);
		
		List<Customer> actCustList = customerServiceImpl.viewAllCustomer();
		assertEquals(custList.size(), actCustList.size());
	}
	
	

}
