package com.payment.paymentService.service;

import com.payment.paymentService.dto.PaymentProcessDto;
import com.payment.paymentService.factory.PaymentFactory;
import com.payment.paymentService.strategy.PaymentMethod;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PaymentGateway {


    private final RedisUtilityService redisUtility;

    public PaymentGateway(RedisUtilityService redisUtility) {
        this.redisUtility = redisUtility;
    }

    public void processPayment(PaymentProcessDto paymentProcessDto) throws BadRequestException {

        String validateUUID=redisUtility.get(paymentProcessDto.getUuid());
        if (validateUUID!=null)
        {
            throw new BadRequestException("Duplicate payment");
        }
        redisUtility.set(paymentProcessDto.getUuid(),"In progress", Duration.ofSeconds(10));
        PaymentMethod paymentMethod = PaymentFactory.getPayment(paymentProcessDto.getType(),paymentProcessDto.getAmount(),paymentProcessDto.getUserId(),paymentProcessDto.getReceiverId(),paymentProcessDto.getUuid(),paymentProcessDto.getData(),redisUtility);
        paymentMethod.pay(paymentProcessDto.getAmount());
    }



}
