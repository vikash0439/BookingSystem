package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Rep;
import com.booking.repository.RepRepository;

@Service
public class RepService {
	
	private final RepRepository repRepository;
	
	public RepService(RepRepository repRepository) {
		this.repRepository = repRepository;
	}
	
	public Rep save(Rep rep) {
		return repRepository.save(rep);
		
	}
	
	public List<Rep> getRep(){
		List<Rep> c = new ArrayList<Rep>();
		repRepository.findAll().forEach(c :: add);
		return c;
	}

	public Rep find(Long repid) {
		return repRepository.findAllByRepid(repid);
	}
	public Rep getByCustomerid(Long repid){
		Rep c = new Rep();
		repRepository.findAllByRepid(repid);
		return c;
	}

	
	


}
