package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

	Service findAllByServiceid(Long serviceid);

	@Query(value = "select servicename from service;", nativeQuery = true)
	public List<String> findName();

	Service findByServicename(String ser);

}
