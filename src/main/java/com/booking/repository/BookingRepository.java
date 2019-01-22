package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.bean.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{

	Booking findAllByServiceid(Long serviceid);
	
	@Query(value = "select * from booking;", nativeQuery = true)
	public List<String> findName();
	
	@Query(value = "select serviceid from booking;", nativeQuery = true)
	public List<Long> findServiceID();
	
	@Query(value = "select servicedate from booking;", nativeQuery = true)
	public List<String> findServiceDate();

	@Query(value = "select * from booking Order by contractid Desc;", nativeQuery = true)
	List<Booking> findAllByOrder();
	
	@Query(value = "select slot from booking WHERE servicedate = ?1 ;", nativeQuery = true)
	String findByServicedate(String date);
	
//	@Query(value = "delete from booking b WHERE b.serviceid = ?1 ;", nativeQuery = true)
//	public void deleteBooking(long serviceid);

	@Query(value = "delete from booking WHERE serviceid = ?1 ;", nativeQuery = true)
	public void deleteByServiceid(Long serviceid);
	   
}
