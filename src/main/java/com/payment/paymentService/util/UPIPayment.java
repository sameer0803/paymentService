package com.payment.paymentService.util;

import com.payment.paymentService.service.RedisUtilityService;
import com.payment.paymentService.template.Payment;
import org.springframework.stereotype.Component;

import java.time.Duration;

public class UPIPayment extends Payment  {

    private final String upiId;
    private final RedisUtilityService redisService;

    public UPIPayment(String senderId, String receiverId, double amount, String upiId, String uuid, RedisUtilityService redisService) {
        super(senderId, receiverId, amount,uuid);
        this.upiId = upiId;
        this.redisService = redisService;
    }

    @Override
    public void validate() {
        if(upiId==null || upiId.isEmpty()) {
            redisService.set(getUuid(),"Failed", Duration.ofMinutes(5));
            throw new IllegalArgumentException("upiId is null or empty");
        }

    }
    @Override
    public void pay(double amount) {
        validate();
        System.out.println("UPI Payment Succesfull");
        redisService.set(getUuid(),"Processed", Duration.ofMinutes(5));
    }
    }

