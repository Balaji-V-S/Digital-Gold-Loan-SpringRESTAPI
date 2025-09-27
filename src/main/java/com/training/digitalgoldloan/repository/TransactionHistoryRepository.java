package com.training.digitalgoldloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.digitalgoldloan.entity.TransactionHistory;
import com.training.digitalgoldloan.entity.enums.TransactionStatus;
import com.training.digitalgoldloan.entity.enums.TransactionOperation;
import com.training.digitalgoldloan.entity.VendorBranch;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

	List<TransactionHistory> findByUserUserId(Integer userId);

	List<TransactionHistory> findByTransactionStatus(TransactionStatus status);

	List<TransactionHistory> findByTransactionOperation(TransactionOperation operation);

	List<TransactionHistory> findByBranch(VendorBranch branch);

	List<TransactionHistory> findByUserUserIdOrderByCreatedAtDesc(Integer userId);

}
