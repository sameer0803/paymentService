package com.payment.paymentService.util;

import com.payment.paymentService.dto.AcknowledgeDto;
import com.payment.paymentService.proxy.AckProxy;
import com.payment.paymentService.service.RedisUtilityService;
import com.payment.paymentService.template.Payment;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CardPayment extends Payment {


    private final String cardPaymentId;
    private final RedisUtilityService redisService;
    private final AckProxy ackProxy;

    public CardPayment(String senderId, String receiverId, double amount, String uuid, String cardPaymentId,RedisUtilityService redisService, AckProxy ackProxy ) {
        super(senderId, receiverId, amount, uuid);

        this.cardPaymentId = cardPaymentId;
        this.redisService = redisService;
        this.ackProxy = ackProxy;
    }

    @Override
    public void validate() {
        if(cardPaymentId==null || cardPaymentId.isEmpty()) {
            redisService.set(getUuid(),"Failed", Duration.ofMinutes(5));
            throw new IllegalArgumentException("upiId is null or empty");
        }


    }

    @Override
    public void pay(double amount)
    {
        validate();
        String response = ackProxy.acknowledge(new AcknowledgeDto(amount,getSenderId(),getReceiverId()));
        log.info("Card Payment is successful"+response);
        redisService.set(getUuid(),"processed sucessfully", Duration.ofMinutes(5));


    }
}
