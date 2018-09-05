package com.booking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Customer;
import com.booking.repository.CustomerRepository;

@Service
public class CustomerServiceImpl {
	
	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
		
	}
	
	public List<Customer> getCustomer(){
		List<Customer> c = new ArrayList<Customer>();
		customerRepository.findAll().forEach(c :: add);
		return c;
	}

	public Customer find(Long customerid) {
		return customerRepository.findAllByCustomerid(customerid);
	}

}
