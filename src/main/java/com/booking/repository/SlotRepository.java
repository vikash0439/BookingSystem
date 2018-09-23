package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long>{

	Slot findAllBySlotid(Long slotid);
    
	@Query(value = "select slot from slot;", nativeQuery = true)
	public List<String> findSlot();
}
