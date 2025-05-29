package com.payment.paymentService.template;

import com.payment.paymentService.strategy.PaymentMethod;
import lombok.Data;

@Data
public abstract class Payment implements PaymentMethod {
    private String senderId;
    private String receiverId;
    private double amount;
    private String uuid;

    public Payment(String senderId, String receiverId, double amount,String uuid) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.uuid = uuid;
    }
    public abstract void validate();
    @Override
   public void pay(double amount){

    }
}
