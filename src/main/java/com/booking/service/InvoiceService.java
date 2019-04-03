package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.bean.Invoice;
import com.booking.bean.Receipt;
import com.booking.repository.InvoiceRepository;
import com.booking.repository.ReceiptRepository;

@Service
public class InvoiceService {
	
private final InvoiceRepository invoiceRepository;
	
	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}
	
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);		
	}
	
	public List<Invoice> getInvoice(){
		List<Invoice> c = new ArrayList<Invoice>();
		invoiceRepository.findAll().forEach(c :: add);
		return c;
	}

	public Invoice find(Long invoiceid) {
		return invoiceRepository.findAllByInvoiceid(invoiceid);
	}

}
