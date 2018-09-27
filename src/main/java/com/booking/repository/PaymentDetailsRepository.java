package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.PaymentDetails;
import com.booking.bean.Slot;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer>{

	
}
