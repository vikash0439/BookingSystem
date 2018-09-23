package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long>{

	Reserve findAllByReserveid(Long reserveid);

}
