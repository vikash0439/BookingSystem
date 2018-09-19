package com.booking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	private String invoicedate;
	private boolean cancelled;
	
	@OneToOne(mappedBy = "invoice")
	private Contract contract;

	public long getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(long invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	
	
	
}
