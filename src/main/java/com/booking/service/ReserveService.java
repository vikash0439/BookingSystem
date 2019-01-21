package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.booking.bean.Reserve;
import com.booking.repository.ReserveRepository;

@Service
public class ReserveService {
	
private final ReserveRepository reserveRepository;
	
	public ReserveService(ReserveRepository reserveRepository) {
		this.reserveRepository = reserveRepository;
	}
	
	public Reserve save(Reserve reserve){
		return reserveRepository.save(reserve);
		
	}
	
	public List<Reserve> getReserve(){
		List<Reserve> c = new ArrayList<Reserve>();
		reserveRepository.findAll().forEach(c :: add);
		return c;
	}

	public Reserve find(Long reserveid) {
		return reserveRepository.findAllByReserveid(reserveid);
	}
	
}
