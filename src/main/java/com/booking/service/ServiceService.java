package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import com.booking.bean.Customer;
import com.booking.bean.Service;
import com.booking.repository.CustomerRepository;
import com.booking.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {
	
private final ServiceRepository serviceRepository;
	
	public ServiceService(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}
	
	public Service save(Service service) {
		return serviceRepository.save(service);
		
	}
	
	public List<Service> getService(){
		List<Service> c = new ArrayList<Service>();
		serviceRepository.findAll().forEach(c :: add);
		return c;
	}

	public Service find(Long serviceid) {
		return serviceRepository.findAllByServiceid(serviceid);
	}

}
