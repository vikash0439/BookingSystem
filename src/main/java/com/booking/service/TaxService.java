package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Tax;
import com.booking.repository.TaxRepository;

@Service
public class TaxService {
	
	private final TaxRepository taxRepository;

	public TaxService(TaxRepository taxRepository) {
		this.taxRepository = taxRepository;
	}
	
	public Tax save(Tax tax) {
		return taxRepository.save(tax);
		
	}
	
	public List<Tax> getService(){
		List<Tax> c = new ArrayList<Tax>();
		taxRepository.findAll().forEach(c :: add);
		return c;
	}

	public Tax find(Long taxid) {
		return taxRepository.findAllByTaxid(taxid);
	}

	

}
