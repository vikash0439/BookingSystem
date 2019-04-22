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

	public  Tax save(Tax tax) {
		// TODO Auto-generated method stub
		return taxRepository.save(tax);
	}

	public Tax find(long taxid) {
		// TODO Auto-generated method stub
		return taxRepository.findAllByTaxid(taxid);
	}
	
	public List<Tax> getTax(){
		List<Tax> c = new ArrayList<Tax>();
		taxRepository.findAll().forEach(c :: add);
		return c;
	}
	public List<String> findSaccode(){
		// TODO Auto-generated method stub
		List<String> saccode = new ArrayList<String>();
		taxRepository.findSac().forEach(saccode :: add);;
		return saccode;
	}
	

}
