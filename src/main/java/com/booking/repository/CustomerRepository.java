package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findAllByCustomerid(Long customerid);
	
	@Query(value = "select customername from customer;", nativeQuery = true)
	public List<String> findName();


	Customer findByCustomername(String customername);
     
}
