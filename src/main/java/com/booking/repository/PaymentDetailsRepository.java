package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer>{

	
}
