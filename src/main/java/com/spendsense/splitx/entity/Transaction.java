package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Group group;
	
	@OneToMany(mappedBy="transaction")
	private List<UserTransactionMapping> transaction;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public List<UserTransactionMapping> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<UserTransactionMapping> transaction) {
		this.transaction = transaction;
	}
	public LocalDateTime getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(LocalDateTime txnTime) {
		this.txnTime = txnTime;
	}
	public double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(double txnAmount) {
		this.txnAmount = txnAmount;
	}
	public int getSoftDelete() {
		return softDelete;
	}
	public void setSoftDelete(int softDelete) {
		this.softDelete = softDelete;
	}
	private LocalDateTime txnTime;
	private double txnAmount;
	private int softDelete;
//	private 
	public Transaction(long id, Group group, List<UserTransactionMapping> transaction, LocalDateTime txnTime,
			double txnAmount, int softDelete) {
		super();
		this.id = id;
		this.group = group;
		this.transaction = transaction;
		this.txnTime = txnTime;
		this.txnAmount = txnAmount;
		this.softDelete = softDelete;
	}
	
}
