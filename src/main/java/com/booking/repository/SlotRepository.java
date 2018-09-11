package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer>{

	Slot findAllBySlotid(Long slotid);
}
