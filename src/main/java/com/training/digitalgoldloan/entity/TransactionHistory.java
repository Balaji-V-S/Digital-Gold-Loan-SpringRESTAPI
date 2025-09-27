package com.training.digitalgoldloan.entity;
import com.training.digitalgoldloan.entity.enums.TransactionOperation;
import com.training.digitalgoldloan.entity.enums.TransactionStatus;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "transaction_history")
public class TransactionHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@NotNull(message = "User is required")
	private User user;

	@ManyToOne
	@JoinColumn(name = "BRANCH_ID")
	@NotNull(message = "Vendor branch is required")
	private VendorBranch branch;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_OPERATION")
	private TransactionOperation transactionOperation;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_STATUS")
	private TransactionStatus transactionStatus;

	@Column(name = "QUANTITY", nullable = false)
	private Double quantity;

	@Column(name = "AMOUNT", nullable = false)
	private Double amount;

	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
}