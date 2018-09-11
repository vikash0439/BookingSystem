package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Purpose;
import com.booking.bean.Slot;
import com.booking.repository.PurposeRepository;
import com.booking.repository.SlotRepository;

@Service
public class PurposeService {
	
	private final PurposeRepository purposeRepository;

	public PurposeService(PurposeRepository purposeRepository) {
		this.purposeRepository = purposeRepository;
	}
	
	public Purpose save(Purpose purpose) {
		return purposeRepository.save(purpose);
		
	}
	
	public List<Purpose> getPurpose(){
		List<Purpose> c = new ArrayList<Purpose>();
		purposeRepository.findAll().forEach(c :: add);
		return c;
	}

	public Purpose find(Long purposeid) {
		return purposeRepository.findAllByPurposeid(purposeid);
	}

	

}
