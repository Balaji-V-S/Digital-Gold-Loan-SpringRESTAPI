package com.training.digitalgoldloan.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "physical_gold_transactions")
public class PhysicalGoldTransaction {
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

	@Column(name = "QUANTITY", nullable = false)
	private Double quantity;

	@ManyToOne
	@JoinColumn(name = "DELIVERY_ADDRESS_ID")
	private Address deliveryAddress;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;

	public PhysicalGoldTransaction(User user, VendorBranch branch, Double quantity, Address deliveryAddress,
			LocalDateTime createdAt) {
		super();
		this.user = user;
		this.branch = branch;
		this.quantity = quantity;
		this.deliveryAddress = deliveryAddress;
		this.createdAt = createdAt;
	}
}