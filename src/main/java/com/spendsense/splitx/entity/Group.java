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
	private String id;
	private String groupName;
	private String groupId;
	private String groupOwner;
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="group")
	List<UserGroupMapping> users;
	
	@OneToMany(mappedBy="group")
	private List<Transaction> transaction;
	
	public Group() {
		
	}

	public Group(String groupId, String groupName, String groupOwner, LocalDateTime groupCreateDate) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupOwner = groupOwner;
		this.createDate = groupCreateDate;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
