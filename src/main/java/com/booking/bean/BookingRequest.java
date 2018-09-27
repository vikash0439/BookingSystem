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
@Table(name="bookingrequest" , schema = "srcpa")
public class BookingRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bookingrequestid", updatable = false, nullable = false)
	private long bookingrequestid;
	private String customername;
	private String bookingpurpose;
	private String language;
	private String genre;
	private String duration;
	private String entryby;
	private String preferreddate;
	private String secondtable;
	
	
	public long getBookingrequestid() {
		return bookingrequestid;
	}
	public void setBookingrequestid(long bookingrequestid) {
		this.bookingrequestid = bookingrequestid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getBookingpurpose() {
		return bookingpurpose;
	}
	public void setBookingpurpose(String bookingpurpose) {
		this.bookingpurpose = bookingpurpose;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEntryby() {
		return entryby;
	}
	public void setEntryby(String entryby) {
		this.entryby = entryby;
	}
	public String getPreferreddate() {
		return preferreddate;
	}
	public void setPreferreddate(String preferreddate) {
		this.preferreddate = preferreddate;
	}
	public String getSecondtable() {
		return secondtable;
	}
	public void setSecondtable(String secondtable) {
		this.secondtable = secondtable;
	}
	
	
	

}
