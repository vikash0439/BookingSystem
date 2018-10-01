package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Rep;

public interface RepRepository extends JpaRepository<Rep, Integer>{

	Rep findAllByRepid(Long repid);

    @Query(value = "select repname from rep ", nativeQuery = true)
	List<String> findRepname();

}
