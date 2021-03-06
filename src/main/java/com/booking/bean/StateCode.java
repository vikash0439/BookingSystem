package com.booking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 17-09-2018
 */

@Entity
@Table(name="statecode" , schema = "srcpa")
public class StateCode {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "statecodeid", updatable = false, nullable = false)
	private long statecodeid;
	private String statename;
	private String statecode;
	
	
	public long getStatecodeid() {
		return statecodeid;
	}
	public void setStatecodeid(long statecodeid) {
		this.statecodeid = statecodeid;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

}
