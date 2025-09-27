package com.training.digitalgoldloan.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.training.digitalgoldloan.entity.enums.TransactionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.digitalgoldloan.entity.TransactionHistory;
import com.training.digitalgoldloan.entity.enums.TransactionStatus;
import com.training.digitalgoldloan.entity.enums.TransactionOperation;
import com.training.digitalgoldloan.exception.TransactionHistoryException;
import com.training.digitalgoldloan.repository.TransactionHistoryRepository;
import com.training.digitalgoldloan.repository.UserRepository;
import com.training.digitalgoldloan.repository.VendorBranchRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VendorBranchRepository vendorBranchRepository;

	@Override
	public List<TransactionHistory> getAllTransactionHistory() {
		return transactionHistoryRepository.findAll();
	}

	@Override
	public TransactionHistory getTransactionHistoryById(Integer transactionId) throws TransactionHistoryException {
		return transactionHistoryRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionHistoryException("TransactionHistroryService.TRANSACTION_NOT_FOUND"));
	}

	@Override
	public List<TransactionHistory> getAllTransactionHistoryByUserId(Integer userId)
			throws TransactionHistoryException {
		userRepository.findById(userId)
				.orElseThrow(() -> new TransactionHistoryException("UserService.USER_NOT_FOUND"));
		List<TransactionHistory> historyList = transactionHistoryRepository.findByUserUserId(userId);
		if (historyList.isEmpty()) {
			throw new TransactionHistoryException("TransactionHistroryService.NO_TRANSCTION_FOR_USER");
		}
		return historyList;
	}

	@Override
	public List<TransactionHistory> getAllSuccessTransactionHistory() {
		return transactionHistoryRepository.findByTransactionStatus(TransactionStatus.Success);
	}

	@Override
	public List<TransactionHistory> getAllFailedTransactionHistory() {
		return transactionHistoryRepository.findByTransactionStatus(TransactionStatus.Failed);
	}

	@Override
	public List<TransactionHistory> getAllTransactionHistoryByTransactionOperation(TransactionOperation transactionOperation)
			throws TransactionHistoryException {
		List<TransactionOperation> validTypes = Arrays.asList(TransactionOperation.Buy, TransactionOperation.Sell,
				TransactionOperation.ConvertToPhysical);
		if (!validTypes.contains(transactionOperation))
			throw new TransactionHistoryException("TransactionHistroryService.INVALID_TYPE");
		List<TransactionHistory> transactions = transactionHistoryRepository.findByTransactionOperation(transactionOperation);
		if (transactions.isEmpty()) {
			throw new TransactionHistoryException("TransactionHistroryService.NO_TRANSACTIONS_AVAILABLE");
		}
		return transactions;
	}

	@Override
	public void addTransactionHistory(TransactionHistory newTransactionHistory) throws TransactionHistoryException {
		Integer branchId = newTransactionHistory.getBranch().getBranchId();
		vendorBranchRepository.findById(branchId)
				.orElseThrow(() -> new TransactionHistoryException("VendorBranchService.BRANCH_NOT_FOUND"));
		Integer userId = newTransactionHistory.getUser().getUserId();
		userRepository.findById(userId)
				.orElseThrow(() -> new TransactionHistoryException("UserService.USER_NOT_FOUND"));
		newTransactionHistory.setCreatedAt(LocalDateTime.now());
		transactionHistoryRepository.save(newTransactionHistory);
	}

	@Override
	public List<TransactionHistory> getTransactionsByUserSorted(Integer userId) {
		return transactionHistoryRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
	}

}
