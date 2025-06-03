package com.payment.paymentService.repository;


import com.payment.paymentService.entity.PaymentProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentProcessRepo extends JpaRepository<PaymentProcess, Long> {
}
