package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer>{

	Contract findAllByContractid(Long contractid);
	
	@Query(value = "select * from contract;", nativeQuery = true)
	public List<String> findName();
	
	@Query(value = "select contractid from contract;", nativeQuery = true)
	public List<Long> findContractID();

	@Query(value = "select COUNT(*) from contract where customerid = ?1 ;", nativeQuery = true)
	public Long CountContract(Long contractid);

	@Query(value = "select * from contract where contractid = ?1 ;", nativeQuery = true)
	Contract getDetail(Long contractid);
     
}
