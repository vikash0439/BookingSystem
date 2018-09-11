package com.booking.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 10-09-2018
 */

@Entity
@Table(name="invoice" , schema = "srcpa")
public class Invoice{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "invoiceid", updatable = false, nullable = false)
	private long invoiceid;
	private LocalDate invoicedate;
	private String invoiceamount;
	private String validity;
	
	
	public long getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(long invoiceid) {
		this.invoiceid = invoiceid;
	}
	public LocalDate getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(LocalDate invoicedate) {
		this.invoicedate = invoicedate;
	}
	public String getInvoiceamount() {
		return invoiceamount;
	}
	public void setInvoiceamount(String invoiceamount) {
		this.invoiceamount = invoiceamount;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	

}
