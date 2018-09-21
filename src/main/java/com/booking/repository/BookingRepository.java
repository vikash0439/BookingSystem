package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Booking;
import com.booking.bean.Contract;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	Booking findAllByServiceid(Long serviceid);
	
	@Query(value = "select * from booking;", nativeQuery = true)
	public List<String> findName();
	
	@Query(value = "select serviceid from booking;", nativeQuery = true)
	public List<Long> findServiceID();
     
}
