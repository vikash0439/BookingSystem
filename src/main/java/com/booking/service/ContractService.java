package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Contract;
import com.booking.repository.ContractRepository;

@Service
public class ContractService {
	
private final ContractRepository contractRepository;
	
	public ContractService(ContractRepository contractRepository) {   // constructor injection with IOC
		this.contractRepository = contractRepository;
	}
	
	
	
	public Contract save(Contract invoice) {
		return contractRepository.save(invoice);		
	}
	
	public List<Contract> getContract(){
		List<Contract> c = new ArrayList<Contract>();
		contractRepository.findAll().forEach(c :: add);
		return c;
	}

	public Contract find(Long contractid) {
		return contractRepository.findAllByContractid(contractid);
	}
	
	public List<Long> getContractID() {
		return contractRepository.findContractID();
	}

}
