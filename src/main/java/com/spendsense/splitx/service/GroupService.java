package com.spendsense.splitx.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.Transaction;
import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.entity.UserGroupMapping;
import com.spendsense.splitx.entity.UserTransactionMapping;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.TransactionRepository;
import com.spendsense.splitx.repository.UserGroupMappingRepository;
import com.spendsense.splitx.repository.UserRepository;
import com.spendsense.splitx.util.GroupCodeGenerator;

@Service
public class GroupService {
	
	@Autowired
	private UserGroupMappingRepository userGroupMappingRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	
	public List<Group> getGroupsByUser(long userId) {
		List<UserGroupMapping> mappingsByUser = userGroupMappingRepository.findAllByUserId(userId);
		List<Group> groupsByUser = new ArrayList<>();
		for(UserGroupMapping mapping: mappingsByUser) {
			groupsByUser.add(mapping.getGroup());
		}
		return groupsByUser;
	}
	
	public Group createGroup(Group group) throws Exception {
		try {
			String groupCode;
			while(true) {
				GroupCodeGenerator groupCodeGenerator = new GroupCodeGenerator();
				groupCode = groupCodeGenerator.generateGroupCode();
				Group newGroup = groupRepository.findByGroupCode(groupCode);
				if(newGroup == null) {
					break;
				}
			}
			System.out.println(groupCode);
			group.setGroupCreateDate(LocalDateTime.now());
			group.setGroupCode(groupCode);
			Group createdGroup = groupRepository.save(group);
			userGroupMappingRepository.save(new UserGroupMapping(group, group.getGroupOwner(), LocalDateTime.now()));
			return createdGroup;
		} catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public Group joinGroup(Group group, long userId) throws Exception {
		
		try {
			
			Group groupObj = groupRepository.findByGroupCode(group.getGroupCode());
			
			if(groupObj == null) {
				throw new Exception("group not found");
			}
			UserGroupMapping userGroups = userGroupMappingRepository.checkUserInGroup(userId, groupObj.getId());
			
			if(userGroups != null) {
				throw new Exception("you are already in a group bhadwe");
			}
			
			User user = userRepository.findById(userId).get();
			
			UserGroupMapping userGroupObj = new UserGroupMapping();
			userGroupObj.setGroup(groupObj);
			userGroupObj.setUser(user);
			userGroupObj.setJoinedTimestamp(LocalDateTime.now());
			
			userGroupMappingRepository.save(userGroupObj);
			
			return groupObj;
			
		} catch(Exception e) {
			throw new Exception(e);
		}
		
	}
	
}
	
 