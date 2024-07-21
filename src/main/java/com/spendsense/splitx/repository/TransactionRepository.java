package com.spendsense.splitx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spendsense.splitx.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
