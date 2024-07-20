package com.spendsense.splitx.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.UserGroupMapping;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.UserGroupMappingRepository;
import com.spendsense.splitx.util.GroupCodeGenerator;

@Service
public class GroupService {
	
	@Autowired
	private UserGroupMappingRepository userGroupMappingrepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	public List<Group> getGroupsByUser(long userId) {
		List<UserGroupMapping> mappingsByUser = userGroupMappingrepository.findAllByUserId(userId);
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
			userGroupMappingrepository.save(new UserGroupMapping(group, group.getGroupOwner(), LocalDateTime.now()));
			return createdGroup;
		} catch(Exception e) {
			throw new Exception(e);
		}
	}
}
