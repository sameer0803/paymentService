package com.payment.paymentService.dto;

import com.payment.paymentService.enums.PaymentTypes;
import lombok.Data;

@Data
public class PaymentProcessDto {

  private  PaymentTypes type;
    private double amount;
    private String userId;
    private String receiverId;
    private String uuid;
    private String data;
}
