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
@Table(name="holiday" , schema = "srcpa")
public class Holiday {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "holidayid", updatable = false, nullable = false)
	private long holidayid;
	private String hdate;
	private String title;
	

}
