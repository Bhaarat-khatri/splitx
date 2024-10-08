package com.spendsense.splitx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.Repayments;
import com.spendsense.splitx.entity.Transaction;
import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.entity.UserGroupMapping;
import com.spendsense.splitx.service.GroupService;
import com.spendsense.splitx.service.TransactionService;
import com.spendsense.splitx.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SplitXController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/api/get-user-details")
	public User getUser(HttpServletRequest request) {
		Long userId = (Long) request.getAttribute("userId");
		return userService.getUserById(userId);
	}

	@GetMapping("/api/get-groups")
	public List<Group> getGroupsByUser(HttpServletRequest request) {
		
		System.out.println(request.getAttribute("userId"));
		Long userId = (Long) request.getAttribute("userId");
		return groupService.getGroupsByUser(userId);
	}

	@PostMapping("/api/create-group")
	public Group createGroup(@RequestBody Group group,HttpServletRequest request) throws Exception {
		try {
			Long userId = (Long) request.getAttribute("userId");

			User user = userService.getUserById(userId);
			
			System.out.println("API hit: " + group.getGroupCode());
			group.setGroupOwner(user);
			return groupService.createGroup(group);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	@PostMapping("/api/join-group")
	public ResponseEntity<Group> joinGroup(@RequestBody Group group,HttpServletRequest request) throws Exception {
		Long userId = (Long) request.getAttribute("userId");

		Group joinedGroup = groupService.joinGroup(group, userId);
		return ResponseEntity.ok(joinedGroup);
	}

	@PostMapping("/api/add-expense")
	public List<Repayments> addExpense(@RequestBody Map<String, Object> payload,HttpServletRequest request)
			throws Exception {

		try {
			Long userId = (Long) request.getAttribute("userId");

			List<Repayments> repayments = transactionService.createTransaction(payload, userId);
			Map<String, Object> response = new HashMap<>();
			response.put("message", repayments);
			return repayments;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@GetMapping("/api/group/{groupCode}")
	public ResponseEntity<List<Transaction>> getGroupTransactions(@PathVariable String groupCode) {
		List<Transaction> txnList = transactionService.getGroupTransactions(groupCode);
		return ResponseEntity.ok(txnList);
	}

	@GetMapping("/api/group/{groupCode}/users")
	public List<UserGroupMapping> getGroupUsers(@PathVariable String groupCode) {
		return groupService.getGroupUsers(groupCode);
	}
	
	@PutMapping("/api/delete-transaction/{txnId}")
	public Transaction deleteTransaction(@PathVariable Long txnId) {
		return transactionService.deleteTransaction(txnId);
	}
	

}
