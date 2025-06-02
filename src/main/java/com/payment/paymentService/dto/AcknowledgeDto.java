package com.payment.paymentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AcknowledgeDto {

    private double amount;
    private String senderId;
    private String receiverId;
}
