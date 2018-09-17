package com.booking.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Vikash Kumar
 * @since 17-09-2018
 */

@Entity
@Table(name="allocation" , schema = "srcpa")
public class Allocation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "allocationid", updatable = false, nullable = false)
	private long allocationid;
	private long contractid;
	private long receiptno;
	private long allocatedamount;
	
	@ManyToOne
	@JoinColumn(name ="contractid", insertable = false, updatable = false)
	private Contract contract;
	@OneToMany(targetEntity = Receipt.class, mappedBy = "contract", cascade=CascadeType.ALL)
	private List<Receipt> recepits;	

	public long getAllocationid() {
		return allocationid;
	}

	public void setAllocationid(long allocationid) {
		this.allocationid = allocationid;
	}

	public long getContractid() {
		return contractid;
	}

	public void setContractid(long contractid) {
		this.contractid = contractid;
	}

	public long getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(long receiptno) {
		this.receiptno = receiptno;
	}

	public long getAllocatedamount() {
		return allocatedamount;
	}

	public void setAllocatedamount(long allocatedamount) {
		this.allocatedamount = allocatedamount;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
    
}
