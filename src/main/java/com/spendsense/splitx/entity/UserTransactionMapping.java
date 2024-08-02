package com.spendsense.splitx.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserTransactionMapping {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="transaction_id")
	@JsonIgnore
	private Transaction transaction;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private double spent;
	private double myShare;
	public UserTransactionMapping() {}
		public UserTransactionMapping(long id, Transaction transaction, User user, double spent, double myShare) {
		super();
		this.id = id;
		this.transaction = transaction;
		this.user = user;
		this.spent = spent;
		this.myShare = myShare;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getSpent() {
		return spent;
	}
	public void setSpent(double spent) {
		this.spent = spent;
	}
	public double getMyShare() {
		return myShare;
	}
	public void setMyShare(double myShare) {
		this.myShare = myShare;
	}
	
	
}
