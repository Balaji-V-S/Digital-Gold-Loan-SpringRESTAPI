package com.training.digitalgoldloan.controller;

import java.util.List;

import com.training.digitalgoldloan.entity.enums.TransactionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.digitalgoldloan.entity.TransactionHistory;
import com.training.digitalgoldloan.entity.enums.TransactionType;
import com.training.digitalgoldloan.exception.TransactionHistoryException;
import com.training.digitalgoldloan.service.TransactionHistoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v3/transaction_history")
public class TransactionHistoryRestController {

	@Autowired
	private TransactionHistoryService transactionHistoryService;

	@Autowired
	private Environment env;

	@GetMapping
	public ResponseEntity<List<TransactionHistory>> getAllTransactionHistory() {
		return ResponseEntity.ok(transactionHistoryService.getAllTransactionHistory());
	}

	@PostMapping("/add")
	public ResponseEntity<String> addTransaction(
			@Valid @RequestBody TransactionHistory newTransaction)
			throws TransactionHistoryException {
		transactionHistoryService.addTransactionHistory(newTransaction);
		return ResponseEntity.ok(env.getProperty("TransactionHistoryService.SUCCESS"));
	}

	@GetMapping("/{transaction_id}")
	public ResponseEntity<TransactionHistory> getTransactionById(@PathVariable("transaction_id") Integer transactionId)
			throws TransactionHistoryException {
		TransactionHistory transaction = transactionHistoryService.getTransactionHistoryById(transactionId);
		return ResponseEntity.ok(transaction);
	}

	@GetMapping("/by_user/{user_id}")
	public ResponseEntity<List<TransactionHistory>> getAllByUserId(@PathVariable("user_id") Integer userId)
			throws TransactionHistoryException {
		return ResponseEntity.ok(transactionHistoryService.getAllTransactionHistoryByUserId(userId));
	}

	@GetMapping("/successful")
	public ResponseEntity<List<TransactionHistory>> getAllSuccessTransactions() {
		return ResponseEntity.ok(transactionHistoryService.getAllSuccessTransactionHistory());
	}

	@GetMapping("/failed")
	public ResponseEntity<List<TransactionHistory>> getAllFailedTransactions() {
		return ResponseEntity.ok(transactionHistoryService.getAllFailedTransactionHistory());
	}

	@GetMapping("/by_type/{transaction_operation}")
	public ResponseEntity<List<TransactionHistory>> getAllByTransactionType(
			@PathVariable("transaction_operation") String transactionOperation) throws TransactionHistoryException {
		TransactionOperation operation = TransactionOperation.valueOf(transactionOperation);
		return ResponseEntity.ok(transactionHistoryService.getAllTransactionHistoryByTransactionOperation(operation));
	}

}
