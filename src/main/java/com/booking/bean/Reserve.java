package com.booking.bean;

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
@Table(name="reserve" , schema = "srcpa")
public class Reserve {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reserveid", updatable = false, nullable = false)
	private long reserveid;
	private String Servicedate;
	private String Servicetime;
	private String slot;
	private String Serviceid;
	private String Customerid;
	private String Reservetitle;
	private String Internalusage;
	
	public Reserve() {
		
	}
	 
	public long getReserveid() {
		return reserveid;
	}


	public void setReserveid(long reserveid) {
		this.reserveid = reserveid;
	}


	public String getSlot() {
		return slot;
	}


	public void setSlot(String slot) {
		this.slot = slot;
	}


	public String getServicedate() {
		return Servicedate;
	}
	public void setServicedate(String string) {
		Servicedate = string;
	}
	public String getServicetime() {
		return Servicetime;
	}
	public void setServicetime(String servicetime) {
		Servicetime = servicetime;
	}
	public String getServiceid() {
		return Serviceid;
	}
	public void setServiceid(String serviceid) {
		Serviceid = serviceid;
	}
	public String getCustomerid() {
		return Customerid;
	}
	public void setCustomerid(String customerid) {
		Customerid = customerid;
	}
	public String getReservetitle() {
		return Reservetitle;
	}
	public void setReservetitle(String reservetitle) {
		Reservetitle = reservetitle;
	}
	public String getInternalusage() {
		return Internalusage;
	}
	public void setInternalusage(String internalusage) {
		Internalusage = internalusage;
	}
	
	
}
