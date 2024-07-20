package com.spendsense.splitx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.UserGroupMapping;
import com.spendsense.splitx.repository.UserGroupMappingRepository;

@Service
public class GroupService {
	
	@Autowired
	private UserGroupMappingRepository userGroupMappingrepository;
	
	public List<Group> getGroupsByUser(long userId) {
		List<UserGroupMapping> mappingsByUser = userGroupMappingrepository.findAllByUserId(userId);
		List<Group> groupsByUser = new ArrayList<>();
		for(UserGroupMapping mapping: mappingsByUser) {
			groupsByUser.add(mapping.getGroup());
		}
		return groupsByUser;
	}
}
