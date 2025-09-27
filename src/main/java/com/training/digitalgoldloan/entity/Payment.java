package com.training.digitalgoldloan.entity;
import com.training.digitalgoldloan.entity.enums.PaymentMethod;
import com.training.digitalgoldloan.entity.enums.TransactionType;
import com.training.digitalgoldloan.entity.enums.PaymentStatus;

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
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENT_ID")
	private Integer paymentId;

	@NotNull(message = "User is required")
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@NotNull(message = "Amount is required")
	@Column(name = "AMOUNT", nullable = false)
	private Double amount;

	@NotNull(message = "Payment method is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "PAYMENT_METHOD")
	private PaymentMethod paymentMethod;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_TYPE")
	private TransactionType transactionType;

	@NotNull(message = "Payment status is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "PAYMENT_STATUS")
	private PaymentStatus paymentStatus;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
}