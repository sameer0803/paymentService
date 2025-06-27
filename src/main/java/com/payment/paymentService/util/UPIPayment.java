package com.payment.paymentService.util;

import com.payment.paymentService.dto.AcknowledgeDto;
import com.payment.paymentService.proxy.AckProxy;
import com.payment.paymentService.service.AckClientRestTemplate;
import com.payment.paymentService.service.RedisUtilityService;
import com.payment.paymentService.template.Payment;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class UPIPayment extends Payment  {

    private final String upiId;
    private final RedisUtilityService redisService;
    private final AckProxy ackProxy;
    private final AckClientRestTemplate ackClientRestTemplate;


    public UPIPayment(String senderId, String receiverId, double amount, String upiId, String uuid, RedisUtilityService redisService, AckProxy ackProxy, AckClientRestTemplate ackClientRestTemplate) {
        super(senderId, receiverId, amount,uuid);
        this.upiId = upiId;
        this.redisService = redisService;
        this.ackProxy = ackProxy;
        this.ackClientRestTemplate = ackClientRestTemplate;
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
       //String response = ackProxy.acknowledge(new AcknowledgeDto(amount,getSenderId(),getReceiverId()));
       String response = ackClientRestTemplate.acknowledgeCall(new AcknowledgeDto(amount,getSenderId(),getReceiverId()));

        System.out.println("UPI Payment Successful");
        log.info(response);
        redisService.set(getUuid(),"Processed", Duration.ofMinutes(5));
    }
    }

