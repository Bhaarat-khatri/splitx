package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.GroupLogs;

@Repository
public interface GroupLogsRepository extends JpaRepository<GroupLogs, Long> {
	
	List<GroupLogs> findAllByGroup(Group group);

}
