package com.booking.service;

import org.springframework.stereotype.Service;

import com.booking.bean.Allocation;
import com.booking.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	
	public AllocationService(AllocationRepository allocationRepository) {
		this.allocationRepository = allocationRepository;
	}
	
	public void saveAllocation(Allocation allocation) {
		allocationRepository.save(allocation);
	}
}
