package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "group_table")
public class Group {

	@Id
	@GeneratedValue
	private long id;
	private String groupName;
	private String groupCode;
	private String groupOwner;
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="group")
	private List<UserGroupMapping> users;
	
	@OneToMany(mappedBy="group")
	private List<Transaction> transaction;
	
	public Group() {
		
	}

	public Group(String groupCode, String groupName, String groupOwner, LocalDateTime groupCreateDate) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.groupOwner = groupOwner;
		this.createDate = groupCreateDate;
	}

	public String getGroupId() {
		return groupCode;
	}

	public void setGroupId(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(String groupOwner) {
		this.groupOwner = groupOwner;
	}

	public LocalDateTime getGroupCreateDate() {
		return createDate;
	}

	public void setGroupCreateDate(LocalDateTime groupCreateDate) {
		this.createDate = groupCreateDate;
	}

}
