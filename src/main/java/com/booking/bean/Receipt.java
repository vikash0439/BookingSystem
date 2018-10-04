package com.booking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 10-09-2018
 */

@Entity
@Table(name="receipt" , schema = "srcpa")
public class Receipt{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "receiptid", updatable = false, nullable = false)
	private long receiptid;
	private String receiptdate;
	private String paidamount;
	private String taxamount;
	private String paymentmode;
	private String finalpayment;
	
	/* Mappings */
	
	@ManyToOne
	@JoinColumn(name ="contractid")
	private Contract contract;
	
	@OneToOne
	@JoinColumn
	private PaymentDetails pdetails;
	
	public PaymentDetails getPdetails() {
		return pdetails;
	}


	public void setPdetails(PaymentDetails pdetails) {
		this.pdetails = pdetails;
	}


	/* Constructors */
	public Receipt() {
		super();
	}
	
	
	public Contract getContract() {
		return contract;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
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
	public String getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(String paidamount) {
		this.paidamount = paidamount;
	}
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getFinalpayment() {
		return finalpayment;
	}
	public void setFinalpayment(String finalpayment) {
		this.finalpayment = finalpayment;
	}


	@Override
	public String toString() {
		return  paidamount;
	}	
	
	

}
