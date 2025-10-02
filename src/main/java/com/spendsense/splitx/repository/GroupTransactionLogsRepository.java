package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.GroupTransactionLogs;
import com.spendsense.splitx.entity.Transaction;

@Repository
public interface GroupTransactionLogsRepository extends JpaRepository<GroupTransactionLogs, Long> {
	List<GroupTransactionLogs> findAllByGroup(Group group);
	List<GroupTransactionLogs> findAllByTransaction(Transaction txn);
}
