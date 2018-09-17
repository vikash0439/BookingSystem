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
@Table(name="tds" , schema = "srcpa")

public class TDS {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tdsid", updatable = false, nullable = false)
	private long tdsid;
	private long receiptno;
	private long tdsamount;
	private String tdscertificateno;

}
