package com.training.digitalgoldloan.service;

import java.util.List;

import com.training.digitalgoldloan.entity.TransactionHistory;
import com.training.digitalgoldloan.entity.enums.TransactionOperation;
import com.training.digitalgoldloan.exception.TransactionHistoryException;

public interface TransactionHistoryService {

	public List<TransactionHistory> getAllTransactionHistory();

	public TransactionHistory getTransactionHistoryById(Integer transactionId) throws TransactionHistoryException;

	public List<TransactionHistory> getAllTransactionHistoryByUserId(Integer userId) throws TransactionHistoryException;

	public List<TransactionHistory> getAllSuccessTransactionHistory();

	public List<TransactionHistory> getAllFailedTransactionHistory();

	public List<TransactionHistory> getAllTransactionHistoryByTransactionOperation(TransactionOperation transactionOperation)
			throws TransactionHistoryException;

	public void addTransactionHistory(TransactionHistory newTransactionHistory) throws TransactionHistoryException;

	public List<TransactionHistory> getTransactionsByUserSorted(Integer userId) throws TransactionHistoryException;
}
