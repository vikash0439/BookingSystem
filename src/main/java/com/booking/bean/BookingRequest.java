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
	

}
