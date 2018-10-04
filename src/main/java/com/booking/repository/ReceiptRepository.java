package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

	Receipt findAllByReceiptid(Long receiptid);

}
