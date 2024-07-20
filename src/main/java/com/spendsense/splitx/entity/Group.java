package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "group_table")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String groupName;
	private String groupCode;
	private LocalDateTime createDate;

	@OneToMany(mappedBy = "group")
	private List<UserGroupMapping> users;

	@OneToMany(mappedBy = "group")
	private List<Transaction> transaction;

	@ManyToOne
	@JoinColumn(name = "group_owner")
	private User groupOwner;

	public Group() {

	}

	public Group(String groupCode, String groupName, User groupOwner, LocalDateTime groupCreateDate) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.groupOwner = groupOwner;
		this.createDate = groupCreateDate;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public User getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(User groupOwner) {
		this.groupOwner = groupOwner;
	}

	public LocalDateTime getGroupCreateDate() {
		return createDate;
	}

	public void setGroupCreateDate(LocalDateTime groupCreateDate) {
		this.createDate = groupCreateDate;
	}

	public long getId() {
		return id;
	}

}
