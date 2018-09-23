package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.bean.Receipt;
import com.booking.bean.Reserve;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

	Reserve findAllByReceiptid(Long receiptid);

}
