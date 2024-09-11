package com.spendsense.splitx.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.Repayments;
import com.spendsense.splitx.entity.Transaction;
import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.entity.UserTransactionMapping;
import com.spendsense.splitx.exception.GroupNotFoundException;
import com.spendsense.splitx.exception.UserAlreadyExistsException;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.RepaymentsRepository;
import com.spendsense.splitx.repository.TransactionRepository;
import com.spendsense.splitx.repository.UserRepository;
import com.spendsense.splitx.repository.UserTransactionMappingRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	class Pair {

        long index;
        double amount;

        Pair(long index, double amount) {
            this.index = index;
            this.amount = amount;
        }
    }
	
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserTransactionMappingRepository userTransactionMappingRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RepaymentsRepository repaymentRepository;

	public List<Repayments> createTransaction(Map<String, Object> payload, long userId) throws Exception {

		try {

			Group group = groupRepository.findByGroupCode(payload.get("groupCode").toString());
			if (group == null) {
				throw new GroupNotFoundException("Group not found");
			}
			User loggedInUser = userRepository.findById(userId).get();
			if (loggedInUser == null) {
				throw new UserAlreadyExistsException("User not found");
			}

			Transaction newTransaction = new Transaction();
			newTransaction.setGroup(group);
			newTransaction.setCreatedBy(loggedInUser);
			newTransaction.setCreatedDate(LocalDateTime.now());
			newTransaction.setSoftDelete(0);
			newTransaction.setTransactionDescription(payload.get("description").toString());
			if(payload.get("settlementType") == null) {
				newTransaction.setTransactionType("expense");
			} else {
				newTransaction.setTransactionType("settlement");
			}

			Transaction transactionObj = transactionRepository.save(newTransaction);

			if (transactionObj == null) {
				throw new Exception("some Issue, Transaction not created");
			}

			Map<String, UserTransactionMapping> allTransactions = new HashMap<>();

			for (Map.Entry<String, Object> element : payload.entrySet()) {
				String key = element.getKey().toString();
				if (key.contains("userSpent") || key.contains("userShare") || key.contains("userId")) {
					int pos = key.indexOf('_') + 1;
					String customUser = key.substring(pos);
					UserTransactionMapping userTransactionMapping = new UserTransactionMapping();

					if (allTransactions.get(customUser) != null) {
						userTransactionMapping = allTransactions.get(customUser);
					}
					userTransactionMapping.setTransaction(transactionObj);

					if (key.contains("userSpent"))
						userTransactionMapping.setSpent(Double.valueOf(element.getValue().toString()));
					else if (key.contains("userShare"))
						userTransactionMapping.setMyShare(Double.valueOf(element.getValue().toString()));
					else if (key.contains("userId"))
						userTransactionMapping
								.setUser(userRepository.findById(Long.valueOf(element.getValue().toString())).get());

					allTransactions.put(customUser, userTransactionMapping);
				}

			}

			List<UserTransactionMapping> userTransactionMappingList = new ArrayList<>();
			Map<Long, Double> balanceMap = new HashMap<Long, Double>();

			for (Map.Entry<String, UserTransactionMapping> element : allTransactions.entrySet()) {
				userTransactionMappingList.add(element.getValue());
				balanceMap.put(element.getValue().getUser().getUserId(), element.getValue().getSpent() - element.getValue().getMyShare());
			}

			userTransactionMappingRepository.saveAll(userTransactionMappingList);
			List<Repayments> repayments =  settleExpenses(balanceMap, transactionObj);
			return repaymentRepository.saveAll(repayments);

		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public List<Repayments> settleExpenses(Map<Long, Double> balanceMap, Transaction txn) {

        List<Pair> debtor = new ArrayList<>();
        List<Pair> creditor = new ArrayList<>();

        for (Map.Entry<Long, Double> entry : balanceMap.entrySet()) {
            if (entry.getValue() < 0) {
                creditor.add(new Pair(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > 0) {
                debtor.add(new Pair(entry.getKey(), entry.getValue()));
            }
        }

        // Sort creditor in descending order of debt
        creditor.sort((a, b) -> Double.compare(b.amount, a.amount));
        // Sort debtor in ascending order of credit
        debtor.sort(Comparator.comparingDouble(a -> a.amount));

        List<Repayments> result = new ArrayList<>();
        int i=0, j = 0;

        while (i < creditor.size() && j < debtor.size()) {
            Pair cr= creditor.get(i);
            Pair db= debtor.get(j);

            double transactionAmount= Math.min(-cr.amount, db.amount);
            
            User from = userRepository.findById(cr.index).get();
            User to = userRepository.findById(db.index).get();
            		
			result.add(new Repayments(from, to, txn, transactionAmount));

            cr.amount += transactionAmount;
            db.amount -= transactionAmount;

            if (cr.amount == 0) i++;
            if (db.amount == 0) j++;
        }

        return result;
    }
	
	public List<Transaction> getGroupTransactions(String groupCode) { 
		Group group = groupRepository.findByGroupCode(groupCode);
		return transactionRepository.findTransactionByGroupId(group.getId());
	}
	
	public Transaction deleteTransaction(Long id) {
		Transaction txn = transactionRepository.findById(id).get();
		txn.setSoftDelete(1);
		return transactionRepository.save(txn);
	}

	

}
