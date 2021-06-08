package com.sbc.gpay.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbc.gpay.entity.Transactions;
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

	List<Transactions> findByFromPhoneOrToPhoneOrderByTransactionDateTime(String phone, String phone2,
			Pageable pageable);

}
