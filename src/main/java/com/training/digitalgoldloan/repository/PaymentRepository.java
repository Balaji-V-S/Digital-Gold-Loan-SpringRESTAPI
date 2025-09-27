package com.training.digitalgoldloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.digitalgoldloan.entity.Payment;
import com.training.digitalgoldloan.entity.enums.PaymentMethod;
import com.training.digitalgoldloan.entity.enums.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findByUserUserId(Integer userId);

	List<Payment> findByPaymentStatus(PaymentStatus status);

	List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);

	List<Payment> findByUserUserIdOrderByCreatedAtDesc(Integer userId);

}
