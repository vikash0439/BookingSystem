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
@Table(name="slab" , schema = "srcpa")
public class Slab {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "slabid", updatable = false, nullable = false)
	private long slabid;
	
	private long slabno;
	private long serviceid;
	private long taxid;
	

}
