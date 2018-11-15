package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.bean.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	Booking findAllByServiceid(Long serviceid);
	
	@Query(value = "select * from booking;", nativeQuery = true)
	public List<String> findName();
	
	@Query(value = "select serviceid from booking;", nativeQuery = true)
	public List<Long> findServiceID();
	
	@Query(value = "select servicedate from booking;", nativeQuery = true)
	public List<String> findServiceDate();

	@Query(value = "select * from booking Order by contractid Desc;", nativeQuery = true)
	List<Booking> findAllByOrder();
     
}
