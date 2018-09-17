package com.booking.bean;

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
@Table(name="performance" , schema = "srcpa")
public class Performance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "performanceid", updatable = false, nullable = false)
	private long performanceid;
	private String showname;
	private String showtime;
	private String showdetails;
	
	@ManyToOne
	@JoinColumn(name ="contractid", insertable = false, updatable = false)
	private Contract contract;

	public long getPerformanceid() {
		return performanceid;
	}

	public void setPerformanceid(long performanceid) {
		this.performanceid = performanceid;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getShowdetails() {
		return showdetails;
	}

	public void setShowdetails(String showdetails) {
		this.showdetails = showdetails;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	

}
