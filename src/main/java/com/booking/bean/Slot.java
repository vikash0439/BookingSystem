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
@Table(name="slot" , schema = "srcpa")
public class Slot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "slotid", updatable = false, nullable = false)
	private long slotid;
	private String slot;
	private String timings;
	
	
	public long getSlotid() {
		return slotid;
	}
	public void setSlotid(long slotid) {
		this.slotid = slotid;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	
	
	
	

}
