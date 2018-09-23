package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long>{

	Tax findAllByTaxid(Long taxid);
}
