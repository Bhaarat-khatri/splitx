package com.spendsense.splitx.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "user_group_mapping_table")
public class UserGroupMapping{
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	Group group;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	private LocalDateTime joinedTimestamp;
	
	public UserGroupMapping() {
		
	}

	public UserGroupMapping(Group group, User user, LocalDateTime joinedTimestamp) {
		super();
		this.group = group;
		this.user = user;
		this.joinedTimestamp = joinedTimestamp;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getJoinedTimestamp() {
		return joinedTimestamp;
	}

	public void setJoinedTimestamp(LocalDateTime joinedTimestamp) {
		this.joinedTimestamp = joinedTimestamp;
	}

	
	
	
	
}
