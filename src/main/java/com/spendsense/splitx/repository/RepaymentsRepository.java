package com.spendsense.splitx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.Repayments;

@Repository
public interface RepaymentsRepository extends JpaRepository<Repayments, Long>{
	
	@Query(value = "INSERT INTO REPAYMENTS (FROM_ID, TO_ID, AMOUNT, TXN_ID) VALUES (:fromId, :toId, :amount, :txnId)", nativeQuery = true)
	Repayments saveById(long fromId, long toId, double amount, long txnId);
}
