package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findAllByCustomerid(Long customerid);
     
}
