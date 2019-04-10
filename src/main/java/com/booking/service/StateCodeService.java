package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.StateCode;
import com.booking.repository.StateCodeRepository;

@Service
public class StateCodeService {
	
	private final StateCodeRepository stateCodeRepository;

	public StateCodeService(StateCodeRepository stateCodeRepository) {
		this.stateCodeRepository = stateCodeRepository;
	}
	
	public StateCode save(StateCode purpose) {
		return stateCodeRepository.save(purpose);		
	}
	
	public List<StateCode> getStateCode(){
		List<StateCode> c = new ArrayList<StateCode>();
		stateCodeRepository.findAll().forEach(c :: add);
		return c;
	}

	public StateCode find(long statecodeid) {
		return stateCodeRepository.findByStatecodeid(statecodeid);		
	}
	
	public String find(String statename) {
		return stateCodeRepository.findByStatename(statename);
		
	}

	public List<String> StateName() {
		// TODO Auto-generated method stub
		return stateCodeRepository.findState();
	}


	

}
