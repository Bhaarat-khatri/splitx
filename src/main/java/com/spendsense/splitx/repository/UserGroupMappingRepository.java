package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.UserGroupMapping;

@Repository
public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, Long> {
	
	List<UserGroupMapping> findAllByUserId(long userId);
}
