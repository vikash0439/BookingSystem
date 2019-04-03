package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Slot;
import com.booking.bean.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer>{

	Slot findAllByVenueid(Long venueid);

	@Query(value = "select venuename from venue;", nativeQuery = true)
	public List<String> findVenue();
	
}
