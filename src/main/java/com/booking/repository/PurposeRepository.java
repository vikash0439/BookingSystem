package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Purpose;

public interface PurposeRepository extends JpaRepository<Purpose, Integer>{

	Purpose findAllByPurposeid(Long purposeid);
}
