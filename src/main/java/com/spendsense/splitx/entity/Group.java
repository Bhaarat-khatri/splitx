package com.spendsense.splitx.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Group {
	
	@Id
	private String groupId;
	private String groupName;
	private String groupOwner;
	private LocalDateTime groupCreateDate;
	private String solutionId;
	
	
	
}
