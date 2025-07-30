package com.payment.paymentService.service;

import com.payment.paymentService.dto.PaymentProcessDto;
import com.payment.paymentService.entity.PaymentProcess;
import com.payment.paymentService.enums.PaymentTypes;
import com.payment.paymentService.factory.PaymentFactory;
import com.payment.paymentService.proxy.AckProxy;
import com.payment.paymentService.repository.PaymentProcessRepo;
import com.payment.paymentService.strategy.PaymentMethod;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.time.Duration;

import static java.time.LocalDateTime.now;

@Service
public class PaymentGateway {
    private final RestTemplate restTemplate;
    private final KafkaProducerService kafkaProducerService;

    private final AckProxy ackProxy;
    private final PaymentProcessRepo paymentProcessRepo;
    private final RedisUtilityService redisUtility;
    private final AckClientRestTemplate   ackClientRestTemplate;

    public PaymentGateway(RestTemplate restTemplate, KafkaProducerService kafkaProducerService, RedisUtilityService redisUtility, PaymentProcessRepo paymentProcessRepo, AckProxy ackProxy, AckClientRestTemplate ackClientRestTemplate) {
        this.restTemplate = restTemplate;
        this.kafkaProducerService = kafkaProducerService;
        this.redisUtility = redisUtility;
        this.paymentProcessRepo = paymentProcessRepo;
        this.ackProxy = ackProxy;
        this.ackClientRestTemplate = ackClientRestTemplate;
    }
    @Transactional
    public void processPayment(PaymentProcessDto paymentProcessDto) throws BadRequestException {
        String validateUUID=redisUtility.get(paymentProcessDto.getUuid());
        if (validateUUID!=null)
        {
            throw new BadRequestException("Duplicate payment");
        }
        redisUtility.set(paymentProcessDto.getUuid(),"In progress", Duration.ofSeconds(10));
        PaymentMethod paymentMethod = PaymentFactory.getPayment(paymentProcessDto.getType(),paymentProcessDto.getAmount(),paymentProcessDto.getUserId(),paymentProcessDto.getReceiverId(),paymentProcessDto.getUuid(),paymentProcessDto.getData(),redisUtility,ackProxy,ackClientRestTemplate);

        paymentMethod.pay(paymentProcessDto.getAmount());
//        paymentProcessRepo.save( dtoToEntity( paymentProcessDto));
        kafkaProducerService.sendMessage(String.valueOf( paymentProcessDto),"payment-data");
    }

    private PaymentProcess dtoToEntity(PaymentProcessDto paymentProcessDto) {
        PaymentProcess paymentProcess = new PaymentProcess();
        paymentProcess.setUuid(paymentProcessDto.getUuid());
        paymentProcess.setSenderId(paymentProcessDto.getUserId());
        paymentProcess.setReceiverId(paymentProcessDto.getReceiverId());
        paymentProcess.setModeOfPayment(paymentProcessDto.getType());
        paymentProcess.setStatus(redisUtility.get(paymentProcessDto.getUuid()));
        paymentProcess.setAmount(paymentProcessDto.getAmount());

        return paymentProcess;

    }


    public void save (PaymentProcessDto paymentProcessDto)
    {

        paymentProcessRepo.save( dtoToEntity( paymentProcessDto));
    }





}
