package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	Invoice findAllByInvoiceid(Long invoiceid);

}
