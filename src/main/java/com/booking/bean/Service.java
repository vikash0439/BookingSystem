package com.booking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 05-09-2018
 */

@Entity
@Table(name="service" , schema = "srcpa")
public class Service {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "serviceid", updatable = false, nullable = false)
	private long serviceid;
	private String servicename;
	private String serviceinuse;
	private String servicecharges;
	private String unit;
	private String cancelcharges;
	
	
	public long getServiceid() {
		return serviceid;
	}
	public void setServiceid(long serviceid) {
		this.serviceid = serviceid;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getServiceinuse() {
		return serviceinuse;
	}
	public void setServiceinuse(String serviceinuse) {
		this.serviceinuse = serviceinuse;
	}
	public String getServicecharges() {
		return servicecharges;
	}
	public void setServicecharges(String servicecharges) {
		this.servicecharges = servicecharges;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCancelcharges() {
		return cancelcharges;
	}
	public void setCancelcharges(String cancelcharges) {
		this.cancelcharges = cancelcharges;
	}
	
	

}
