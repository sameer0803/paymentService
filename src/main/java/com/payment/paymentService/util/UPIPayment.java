package com.payment.paymentService.util;

import com.payment.paymentService.dto.AcknowledgeDto;
import com.payment.paymentService.proxy.AckProxy;
import com.payment.paymentService.service.RedisUtilityService;
import com.payment.paymentService.template.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
public class UPIPayment extends Payment  {

    private final String upiId;
    private final RedisUtilityService redisService;
    private AckProxy ackProxy;




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
        ackProxy.acknowledge(new AcknowledgeDto(amount,getSenderId(),getReceiverId()));
        System.out.println("UPI Payment Succesfull");
        log.info("UPI Payment Succesfull");
        redisService.set(getUuid(),"Processed", Duration.ofMinutes(5));
    }
    }

