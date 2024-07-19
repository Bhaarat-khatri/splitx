package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "user_table")
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String email;
	
	@OneToMany(mappedBy="user")
	List<UserGroupMapping> groups;
	
	@OneToMany(mappedBy="user")
	List<UserTransactionMapping> txn;
	
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

}
