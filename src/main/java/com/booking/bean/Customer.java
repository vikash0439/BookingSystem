package com.booking.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 05-09-2018
 */

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customerid", updatable = false, nullable = false)
	private long customerid;
	private String customerName;
	private String landline;
	private String website;
	private String address;
	private String category;
	private String remark;
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "repid")
	private Rep rep;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//	private Collection<Rep> rep=new ArrayList<Rep>();
	
	
	public Rep getRep() {
		return rep;
	}
	public void setRep(Rep rep) {
		this.rep = rep;
	}

	public long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customerName=" + customerName + ", landline=" + landline
				+ ", website=" + website + ", address=" + address + ", category=" + category + ", remark=" + remark
				+ ", rep=" + rep + "]";
	}
	

}
