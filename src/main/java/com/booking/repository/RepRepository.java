package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Rep;

public interface RepRepository extends JpaRepository<Rep, Integer>{

	Rep findAllByRepid(Long repid);

}
