package com.payment.paymentService.util;


import com.payment.paymentService.template.Payment;

public class Wallet extends Payment {

    private final String walletId;

    public Wallet(String senderId, String receiverId, double amount, String uuid, String walletId) {
        super(senderId, receiverId, amount, uuid);
        this.walletId = walletId;
    }

    @Override
    public void validate() {

    }
}
