package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	public List<Transaction> findAllByGroup(Group gooup);
}
