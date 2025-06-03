package com.payment.paymentService.entity;

import com.payment.paymentService.enums.PaymentTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "payment_process",uniqueConstraints = {@UniqueConstraint(name="uq_uuid",columnNames = "uuid")},indexes = {@Index(name ="idx_created_at", columnList = "created_at")})
public class PaymentProcess {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(name = "amount", nullable = false)
 private double amount;
 @Column(name = "sender_id", nullable = false)
 private String senderId;
 @Column(name = "receiver_id", nullable = false)
 private String receiverId;
 @Enumerated(EnumType.STRING)
 @Column(name = "mode_of_payment", nullable = false)
 private PaymentTypes modeOfPayment;
 @Column(name = "uuid", unique = true, nullable = false)
 private String uuid;
 @Column(name = "status", nullable = false)
 private String status;
 @Column(name = "created_at", nullable = false)
 private LocalDateTime createdAt=LocalDateTime.now();

 public PaymentProcess(Long id, double amount, String senderId, String receiverId, PaymentTypes modeOfPayment, String uuid, String status, LocalDateTime createdAt) {
  this.id = id;
  this.amount = amount;
  this.senderId = senderId;
  this.receiverId = receiverId;
  this.modeOfPayment = modeOfPayment;
  this.uuid = uuid;
  this.status = status;
  this.createdAt = createdAt;
 }

}
