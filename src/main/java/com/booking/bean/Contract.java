package com.booking.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Vikash Kumar
 * @since 11-09-2018
 */

@Entity
@Table(name="contract" , schema = "srcpa")
public class Contract implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "contractid")
	private long contractid;
	private String bookingdate;
	private String purpose;
	private String baseprice;
	private String taxamount;
	private String pact;
	private String paymentstatus;
	private String override;
	private String slabno;
	
	
	/* Mapping */

	@OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
	private Invoice invoice;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity = Booking.class, mappedBy = "contract", cascade=CascadeType.ALL)
	private List<Booking> bookings;	
	@OneToMany(targetEntity = Performance.class, mappedBy = "contract", cascade=CascadeType.ALL)
	private List<Performance> performances;	
	@OneToMany(targetEntity = Allocation.class, mappedBy = "contract", cascade=CascadeType.ALL)
	private List<Allocation> allocation;
	@OneToMany(targetEntity = Receipt.class, mappedBy = "contract", cascade=CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Receipt> receipt;
	@ManyToOne
	@JoinColumn(name ="customerid")
	private Customer customer;
	
	
	
	public List<Receipt> getReceipt() {
		return receipt;
	}
	public void setReceipt(List<Receipt> receipt) {
		this.receipt = receipt;
	}
	public long getContractid() {
		return contractid;
	}
	public void setContractid(long contractid) {
		this.contractid = contractid;
	}
	public String getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(String bookingdate) {
		this.bookingdate = bookingdate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(String baseprice) {
		this.baseprice = baseprice;
	}
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	public String getPact() {
		return pact;
	}
	public void setPact(String pact) {
		this.pact = pact;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getOverride() {
		return override;
	}
	public void setOverride(String override) {
		this.override = override;
	}
	public String getSlabno() {
		return slabno;
	}
	public void setSlabno(String slabno) {
		this.slabno = slabno;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public List<Performance> getPerformances() {
		return performances;
	}
	public void setPerformances(List<Performance> performances) {
		this.performances = performances;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public List<Allocation> getAllocation() {     // IOC class setter inhjection
		return allocation;
	}
	public void setAllocation(List<Allocation> allocation) {   // IOC class with getter injection
		this.allocation = allocation;
	}

}
