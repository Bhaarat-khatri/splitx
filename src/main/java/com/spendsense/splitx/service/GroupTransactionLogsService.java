package com.spendsense.splitx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.GroupTransactionLogs;
import com.spendsense.splitx.entity.Transaction;
import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.GroupTransactionLogsRepository;
import com.spendsense.splitx.repository.UserRepository;

@Service
public class GroupTransactionLogsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupTransactionLogsRepository groupTransactionLogsRepository;

	@Autowired
	private GroupRepository groupRepository;

	public GroupTransactionLogs saveLog(Transaction transaction, Map<String, Object> payload, Long userId) {
		// TODO Auto-generated method stub
		User createdBy = userRepository.findById(userId).get();
		Double amount = Double.parseDouble(payload.get("amount").toString());
		Group group = groupRepository.findByGroupCode(payload.get("groupCode").toString());
		GroupTransactionLogs log = GroupTransactionLogs.builder()
				.createdBy(createdBy)
				.amount(amount)
				.group(group)
				.transaction(transaction)
				.build();
		log.setOperation(payload.containsKey("operation") ? payload.get("operation").toString() : "add");
		return groupTransactionLogsRepository.save(log);
	}

	public List<GroupTransactionLogs> getTransactionLogs(String groupCode) {
		// TODO Auto-generated method stub
		Group group = groupRepository.findByGroupCode(groupCode);
		return groupTransactionLogsRepository.findAllByGroup(group);
	}
}
