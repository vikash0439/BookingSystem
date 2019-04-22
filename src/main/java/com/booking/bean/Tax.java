package com.booking.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Tax {

	@Id
	@GeneratedValue
	private Long taxid;
	private String saccode;
	private String description;
	private String gst;
	
	public Long getTaxid() {
		return taxid;
	}
	public void setTaxid(Long taxid) {
		this.taxid = taxid;
	}
	public String getSaccode() {
		return saccode;
	}
	public void setSaccode(String saccode) {
		this.saccode = saccode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
}
