package com.booking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 07-09-2018
 */

@Entity
@Table(name="tax", schema = "srcpa")
public class Tax {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "taxid", updatable = false, nullable = false)
	private long taxid;
	private String taxname;
	private String taxform;
	private String taxcharges;
	private String applied;
	private String taxinuse;
	
	
	public long getTaxid() {
		return taxid;
	}
	public void setTaxid(long taxid) {
		this.taxid = taxid;
	}
	public String getTaxname() {
		return taxname;
	}
	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}
	public String getTaxform() {
		return taxform;
	}
	public void setTaxform(String taxform) {
		this.taxform = taxform;
	}
	public String getTaxcharges() {
		return taxcharges;
	}
	public void setTaxcharges(String taxcharges) {
		this.taxcharges = taxcharges;
	}
	public String getApplied() {
		return applied;
	}
	public void setApplied(String applied) {
		this.applied = applied;
	}
	public String getTaxinuse() {
		return taxinuse;
	}
	public void setTaxinuse(String taxinuse) {
		this.taxinuse = taxinuse;
	}
	
	

}
