package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Receipt;
import com.booking.repository.ReceiptRepository;

@Service
public class ReceiptService {
	
private final ReceiptRepository receiptRepository;
	
	public ReceiptService(ReceiptRepository receiptRepository) {
		this.receiptRepository = receiptRepository;
	}
	
	public Receipt save(Receipt receipt) {
		return receiptRepository.save(receipt);		
	}
	
	public List<Receipt> getReceipt(){
		List<Receipt> c = new ArrayList<Receipt>();
		receiptRepository.findAll().forEach(c :: add);
		return c;
	}

	public Receipt find(Long receiptid) {
		return receiptRepository.findAllByReceiptid(receiptid);
	}

	public String PaidAmount(long receiptid) {
		// TODO Auto-generated method stub
		return receiptRepository.findAmount(receiptid);
	}

}
