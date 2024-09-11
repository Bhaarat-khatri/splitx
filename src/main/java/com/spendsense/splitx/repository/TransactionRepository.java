package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	public List<Transaction> findAllByGroup(Group group);
	
	@Query(value = "SELECT t.* FROM transaction t JOIN group_table g ON t.group_id = g.id WHERE t.soft_delete = 0 AND t.group_id = :groupId", nativeQuery = true)
	List<Transaction> findTransactionByGroupId(@Param("groupId") Long groupId);
}
