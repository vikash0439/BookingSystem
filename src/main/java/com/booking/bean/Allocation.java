package com.booking.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Allocation{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long allocationid;
	private String contractid;
	private String receiptno;
	private String allocatedamount;
		
	public long getAllocationid() {
		return allocationid;
	}
	public void setAllocationid(long allocationid) {
		this.allocationid = allocationid;
	}
	public String getContractid() {
		return contractid;
	}
	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	public String getAllocatedamount() {
		return allocatedamount;
	}
	public void setAllocatedamount(String allocatedamount) {
		this.allocatedamount = allocatedamount;
	}
	
	
}
