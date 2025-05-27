package com.payment.paymentService.template;


import com.payment.paymentService.strategy.PaymentMethod;
import lombok.Data;

@Data
public abstract class Payment implements PaymentMethod {
    private String senderId;
    private String receiverId;
    private double amount;

    public Payment(String senderId, String receiverId, double amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }
    abstract void validate();
    @Override
    void pay(double amount){

    };
}
