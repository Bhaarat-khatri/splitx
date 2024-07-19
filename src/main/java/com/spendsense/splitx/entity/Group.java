package com.spendsense.splitx.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "user_group")
public class Group {

	@Id
	private String groupId;
	private String groupName;
	private String groupOwner;
	private LocalDateTime groupCreateDate;
	
	@OneToMany(mappedBy="group")
	List<UserGroupMapping> users;
	
//	@OneToMany(mappedBy="group")
//	private List<Transaction> txns;
	
	public Group() {
		
	}

	public Group(String groupId, String groupName, String groupOwner, LocalDateTime groupCreateDate) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupOwner = groupOwner;
		this.groupCreateDate = groupCreateDate;
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
		return groupCreateDate;
	}

	public void setGroupCreateDate(LocalDateTime groupCreateDate) {
		this.groupCreateDate = groupCreateDate;
	}

}
