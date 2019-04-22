package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.bean.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer>{
	

}
