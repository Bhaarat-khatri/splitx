package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1)
	private long id;
	private String name;
	private String email;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserGroupMapping> groups;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserTransactionMapping> txn;

	@OneToMany(mappedBy = "groupOwner")
	private List<Group> ownedGroups;

	@OneToMany(mappedBy = "createdBy")
	private List<Transaction> createdTransaction;

	@OneToMany(mappedBy = "updatedBy")
	private List<Transaction> updatedTransaction;

	@OneToMany(mappedBy = "from")
	@JsonIgnore
	private List<Repayments> fromRepayments;

	@OneToMany(mappedBy = "to")
	@JsonIgnore
	private List<Repayments> toRepaymnets;

	public User() {

	}

	public User(long userId, String name, String email, LocalDateTime joinedTimestamp) {
		super();
		this.id = userId;
		this.name = name;
		this.email = email;
	}

	public long getUserId() {
		return id;
	}

	public void setUserId(long userId) {
		this.id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Repayments> getFromRepayments() {
		return fromRepayments;
	}

	public void setFromRepayments(List<Repayments> fromRepayments) {
		this.fromRepayments = fromRepayments;
	}

	public List<Repayments> getToRepaymnets() {
		return toRepaymnets;
	}

	public void setToRepaymnets(List<Repayments> toRepaymnets) {
		this.toRepaymnets = toRepaymnets;
	}

}
