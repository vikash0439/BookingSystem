package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Purpose;

public interface PurposeRepository extends JpaRepository<Purpose, Integer>{

	Purpose findAllByPurposeid(Long purposeid);
	@Query(value = "select purpose from purpose;", nativeQuery = true)
	public List<String> findPurpose();
}
