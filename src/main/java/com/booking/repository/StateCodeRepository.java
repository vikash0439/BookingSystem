package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.StateCode;

public interface StateCodeRepository extends JpaRepository<StateCode, Integer>{
	
	
	@Query(value = "select statecode from statecode;", nativeQuery = true)
	public List<String> findStateCode();

	public StateCode findByStatecodeid(long statecodeid);

	@Query(value = "select statename from statecode;", nativeQuery = true)
	public List<String> findState();

	@Query(value = "select statecode from statecode where statename = ?1", nativeQuery = true)
	public String findByStatename(String statename);
	
}
