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
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "virtual_gold_holdings")
public class VirtualGoldHolding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOLDING_ID")
	private Integer holdingId;

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

	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
}