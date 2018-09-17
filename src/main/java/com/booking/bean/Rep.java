package com.booking.bean;

import javax.persistence.CascadeType;
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
 * @since 05-09-2018
 */

@Entity
@Table(name="rep" , schema = "srcpa")
public class Rep{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "repid", updatable = false, nullable = false)
	private long repid;
	private String repname;
	private String mob;
	private String email;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private Customer customer;

	public long getRepid() {
		return repid;
	}

	public void setRepid(long repid) {
		this.repid = repid;
	}

	public String getRepname() {
		return repname;
	}

	public void setRepname(String repname) {
		this.repname = repname;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
