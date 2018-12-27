package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.PaymentDetails;
import com.booking.bean.Slot;
import com.booking.repository.PaymentDetailsRepository;
import com.booking.repository.SlotRepository;

@Service
public class PaymentDetailsService {
	
	private final PaymentDetailsRepository paymentDetailsRepository;

	public PaymentDetailsService(PaymentDetailsRepository paymentDetailsRepository) {
		this.paymentDetailsRepository = paymentDetailsRepository;
	}
	
	
	public PaymentDetails save(PaymentDetails payment) {
		return paymentDetailsRepository.save(payment);
		
	}
}
