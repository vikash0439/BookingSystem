package com.booking.bean;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 11-09-2018
 */

@Entity
@Table(name="contract" , schema = "srcpa")
public class Contract {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "contractid", updatable = false, nullable = false)
	private long contractid;
	private String purpose;
	private String showname;
	private String showdetail;
	private String showtime;
	private String CustomerName;
	private String repname;
	private String repemail;
	private String repmobile;
	private LocalDate showdate;
	private String slot;
	private String services;
	private String charges;
	private String taxamount;
	private String total;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	public long getContractid() {
		return contractid;
	}
	public void setContractid(long contractid) {
		this.contractid = contractid;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
	public String getShowdetail() {
		return showdetail;
	}
	public void setShowdetail(String showdetail) {
		this.showdetail = showdetail;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getRepname() {
		return repname;
	}
	public void setRepname(String repname) {
		this.repname = repname;
	}
	public String getRepemail() {
		return repemail;
	}
	public void setRepemail(String repemail) {
		this.repemail = repemail;
	}
	public String getRepmobile() {
		return repmobile;
	}
	public void setRepmobile(String repmobile) {
		this.repmobile = repmobile;
	}
	public LocalDate getShowdate() {
		return showdate;
	}
	public void setShowdate(LocalDate showdate) {
		this.showdate = showdate;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}

}
