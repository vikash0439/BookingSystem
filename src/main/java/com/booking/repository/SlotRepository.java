package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer>{

	Slot findAllBySlotid(Long slotid);
    
	@Query(value = "select slot from slot;", nativeQuery = true)
	public List<String> findSlot();
 
	@Query(value = "SELECT u.timings FROM slot u WHERE u.slot = ?1 ", nativeQuery = true)
	String findBySLot(String timing);




	
}
