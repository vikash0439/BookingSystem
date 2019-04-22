package com.booking.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Receipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long receiptid;
	private String receiptdate;
	private String amount;
	private String mode;
	private String description;
	
	@ManyToOne
	private Customer customer;
	
	@OneToOne
	@JoinColumn
	private PaymentDetails pdetails;
	
	public PaymentDetails getPdetails() {
		return pdetails;
	}
	public void setPdetails(PaymentDetails pdetails) {
		this.pdetails = pdetails;
	}
	
	public long getReceiptid() {
		return receiptid;
	}
	public void setReceiptid(long receiptid) {
		this.receiptid = receiptid;
	}
	public String getReceiptdate() {
		return receiptdate;
	}
	public void setReceiptdate(String receiptdate) {
		this.receiptdate = receiptdate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
		
}
