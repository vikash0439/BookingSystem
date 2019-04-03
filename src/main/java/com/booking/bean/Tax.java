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
	private String igst;
	private String sgst;
	private String cgst;
	
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
	public String getIgst() {
		return igst;
	}
	public void setIgst(String igst) {
		this.igst = igst;
	}
	public String getSgst() {
		return sgst;
	}
	public void setSgst(String sgst) {
		this.sgst = sgst;
	}
	public String getCgst() {
		return cgst;
	}
	public void setCgst(String cgst) {
		this.cgst = cgst;
	}
	
	
	
	
}
