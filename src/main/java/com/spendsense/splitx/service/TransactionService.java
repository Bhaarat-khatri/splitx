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
import com.spendsense.splitx.entity.UserTransactionMapping;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.TransactionRepository;
import com.spendsense.splitx.repository.UserRepository;
import com.spendsense.splitx.repository.UserTransactionMappingRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserTransactionMappingRepository userTransactionMappingRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired 
	private UserRepository userRepository;
	
	public void createTransaction(Map<String,Object> payload,long userId) throws Exception {
		
	
		// create new txn
		// get txn idy
		//
		try {
		
		
		Group group  = groupRepository.findByGroupCode(payload.get("groupCode").toString());
		if(group == null) {
			throw new Exception("Group not found");
		}
		User loggedUser = userRepository.findById(userId).get();
		if(loggedUser == null) {
			throw new Exception("User not found");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setGroup(group);
		newTransaction.setCreatedBy(loggedUser);
		newTransaction.setCreatedDate(LocalDateTime.now());
		newTransaction.setSoftDelete(0);
		newTransaction.setTransactionDescription(payload.get("description").toString());
		
		Transaction transactionObj = transactionRepository.save(newTransaction);
		
		if(transactionObj == null) {
			throw new Exception("some Issue, Transaction not created");
		}
		
		
		Map<String , UserTransactionMapping> allTransactions = new HashMap<>(); 
		
		for(Map.Entry<String,Object> element: payload.entrySet()) {
			String key = element.getKey().toString();
			if(key.contains("userSpent") || key.contains("userShare") || key.contains("userId")) {
				int pos = key.indexOf('_') + 1;
				String customUser = key.substring(pos);
				UserTransactionMapping userTransactionMapping = new UserTransactionMapping();

				if(allTransactions.get(customUser) != null)  {
					userTransactionMapping = allTransactions.get(customUser);
				} 		
				userTransactionMapping.setTransaction(transactionObj);

				if(key.contains("userSpent"))
					userTransactionMapping.setSpent(Long.valueOf(element.getValue().toString()));
				else if(key.contains("userShare"))
					userTransactionMapping.setMyShare(Long.valueOf(element.getValue().toString()));
				else if(key.contains("userId"))
					userTransactionMapping.setUser(userRepository.findById(Long.valueOf(element.getValue().toString())).get());


				allTransactions.put(customUser,userTransactionMapping);
			}
			
	

		}
		
		List<UserTransactionMapping> userTransactionMappingList = new ArrayList<>();
		
		for(Map.Entry<String,UserTransactionMapping> element: allTransactions.entrySet()) {
			userTransactionMappingList.add(element.getValue());
		}
		
		userTransactionMappingRepository.saveAll(userTransactionMappingList);
		
		} catch(Exception e) {
			throw new Exception(e);
		}
		
	}

}
