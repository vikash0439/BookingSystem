package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

	Service findAllByServiceid(Long serviceid);

}
