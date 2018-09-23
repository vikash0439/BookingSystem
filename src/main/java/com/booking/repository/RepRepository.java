package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Rep;

public interface RepRepository extends JpaRepository<Rep, Long>{

	Rep findAllByRepid(Long repid);

}
