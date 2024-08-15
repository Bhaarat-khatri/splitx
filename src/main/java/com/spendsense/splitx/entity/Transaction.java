package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JsonIgnore
	private Group group;

	@OneToMany(mappedBy = "transaction")
	private List<UserTransactionMapping> userTransactions;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private String transactionDescription;
	
	@ManyToOne
	@JsonIgnore
	private User createdBy;

	@OneToMany(mappedBy = "txn")
	private List<Repayments> repayments;

	@ManyToOne
	@JsonIgnore
	private User updatedBy;

	private int softDelete;

	public Transaction() {
	}

	public Transaction(long id, Group group, List<UserTransactionMapping> transaction, LocalDateTime createdDate,
			LocalDateTime updatedDate, User createdBy, User updatedBy, LocalDateTime txnTime, double txnAmount,
			int softDelete) {
		super();
		this.id = id;
		this.group = group;
		this.userTransactions = transaction;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.softDelete = softDelete;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

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

	public List<UserTransactionMapping> getUserTransactions() {
		return userTransactions;
	}

	public void setUserTransactions(List<UserTransactionMapping> transaction) {
		this.userTransactions = transaction;
	}

	public int getSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(int softDelete) {
		this.softDelete = softDelete;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public List<Repayments> getRepayments() {
		return repayments;
	}

	public void setRepayments(List<Repayments> repayments) {
		this.repayments = repayments;
	}
}
