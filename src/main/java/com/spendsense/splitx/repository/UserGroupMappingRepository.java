package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.UserGroupMapping;

@Repository
public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, Long> {
	
	List<UserGroupMapping> findAllByUserId(long userId);
	
	@Query(value = "SELECT * FROM user_group_mapping_table WHERE USER_ID = :userId and GROUP_ID = :groupId",nativeQuery = true)
	UserGroupMapping checkUserInGroup(long userId, long groupId);

}
