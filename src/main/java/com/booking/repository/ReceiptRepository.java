package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bean.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

	Receipt findAllByReceiptid(Long receiptid);

	@Query(value = "select paidamount from receipt where receiptid = ?1 ;", nativeQuery = true)
	String findAmount(long receiptid);
	
	@Query(value = "select receiptid from receipt;", nativeQuery = true)
	public List<Long> getAllReceiptNo();

}
