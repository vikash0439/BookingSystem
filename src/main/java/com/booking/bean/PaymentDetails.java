package com.booking.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 17-09-2018
 */

@Entity
@Table(name="paymentdetails" , schema = "srcpa")
public class PaymentDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "paymentdetailsid", updatable = false, nullable = false)
	private long paymentdetailsid;	
	private String modeid;
	private String modedate;
	private String modebank;
	private String paidby;
	private String credit;
	
	@OneToOne(mappedBy = "pdetails", cascade = CascadeType.ALL)
	private Receipt receipt;

	public long getPaymentdetailsid() {
		return paymentdetailsid;
	}

	public void setPaymentdetailsid(long paymentdetailsid) {
		this.paymentdetailsid = paymentdetailsid;
	}

	public String getModedate() {
		return modedate;
	}

	public void setModedate(String modedate) {
		this.modedate = modedate;
	}

	public String getModebank() {
		return modebank;
	}

	public void setModebank(String modebank) {
		this.modebank = modebank;
	}

	public String getPaidby() {
		return paidby;
	}

	public void setPaidby(String paidby) {
		this.paidby = paidby;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public String getModeid() {
		return modeid;
	}

	public void setModeid(String modeid) {
		this.modeid = modeid;
	}

	

}
