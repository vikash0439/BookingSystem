package com.booking.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 17-09-2018
 */

@Entity
@Table(name="booking" , schema = "srcpa")
public class Booking implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "serviceid")
	private long serviceid;
	private String servicename;
	private String servicedate;
	private String servicetime;
	private String servicecost;
	private String serviceused;
	private String slot;

	
	@ManyToOne
	@JoinColumn(name ="contractid")
	private Contract contract;
	
	
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public long getServiceid() {
		return serviceid;
	}
	public void setServiceid(long serviceid) {
		this.serviceid = serviceid;
	}
	public String getServicedate() {
		return servicedate;
	}
	public void setServicedate(String servicedate) {
		this.servicedate = servicedate;
	}
	public String getServicetime() {
		return servicetime;
	}
	public void setServicetime(String servicetime) {
		this.servicetime = servicetime;
	}
	public String getServicecost() {
		return servicecost;
	}
	public void setServicecost(String servicecost) {
		this.servicecost = servicecost;
	}
	public String getServiceused() {
		return serviceused;
	}
	public void setServiceused(String serviceused) {
		this.serviceused = serviceused;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
		
	@Override
	public String toString() {
		return  servicedate +" from " +servicetime ;
	}
}
