package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Booking;
import com.booking.bean.Contract;
import com.booking.bean.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Integer>{

	Performance findAllByPerformanceid(Long performanceid);
	
	@Query(value = "select * from performance;", nativeQuery = true)
	public List<String> findName();
	
	@Query(value = "select performanceid from performance;", nativeQuery = true)
	public List<Long> findPerformanceID();
     
}
