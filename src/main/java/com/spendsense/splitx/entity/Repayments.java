package com.spendsense.splitx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Repayments {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User from;
	
	@ManyToOne
	private User to;
	
	@ManyToOne
	@JsonIgnore
	private Transaction txn;
	
	private double amount;
	
	public Repayments() {
		
	}

	public Repayments(long id, User from, User to, Transaction txn, double amount) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.txn = txn;
		this.amount = amount;
	}
	
	public Repayments(User from, User to, Transaction txn, double amount) {
		super();
		this.from = from;
		this.to = to;
		this.txn = txn;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public Transaction getTxn() {
		return txn;
	}

	public void setTxn(Transaction txn) {
		this.txn = txn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
