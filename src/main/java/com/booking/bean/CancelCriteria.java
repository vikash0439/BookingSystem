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
@Table(name="cancelcriteria" , schema = "srcpa")
public class CancelCriteria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cancelcriteriaid", updatable = false, nullable = false)
	private long cancelcriteriaid;
	private String canceltimemin;
	private String canceltimemax;
	private String cancelcharges;
	
	
	public long getCancelcriteriaid() {
		return cancelcriteriaid;
	}
	public void setCancelcriteriaid(long cancelcriteriaid) {
		this.cancelcriteriaid = cancelcriteriaid;
	}
	public String getCanceltimemin() {
		return canceltimemin;
	}
	public void setCanceltimemin(String canceltimemin) {
		this.canceltimemin = canceltimemin;
	}
	public String getCanceltimemax() {
		return canceltimemax;
	}
	public void setCanceltimemax(String canceltimemax) {
		this.canceltimemax = canceltimemax;
	}
	public String getCancelcharges() {
		return cancelcharges;
	}
	public void setCancelcharges(String cancelcharges) {
		this.cancelcharges = cancelcharges;
	}
	

}
