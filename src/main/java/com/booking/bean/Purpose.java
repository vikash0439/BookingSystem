package com.booking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 11-09-2018
 */

@Entity
@Table(name="purpose" , schema = "srcpa")
public class Purpose {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "purposeid", updatable = false, nullable = false)
	private long purposeid;
	private String purpose;
	
	
	public long getPurposeid() {
		return purposeid;
	}
	public void setPurposeid(long purposeid) {
		this.purposeid = purposeid;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	

}
