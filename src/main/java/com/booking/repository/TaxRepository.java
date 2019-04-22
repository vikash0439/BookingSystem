package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.bean.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{
	
	@Query(value = "SELECT * FROM tax WHERE taxid = ?1 ", nativeQuery = true)
	Tax findAllByTaxid(long taxid);

	@Query(value = "select saccode from tax;", nativeQuery = true)
	List<String> findSac();

}
