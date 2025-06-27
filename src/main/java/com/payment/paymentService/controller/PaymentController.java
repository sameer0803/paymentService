package com.payment.paymentService.controller;

import com.payment.paymentService.dto.PaymentProcessDto;
import com.payment.paymentService.service.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    RestTemplate restTemplate = new RestTemplate();

    private final PaymentGateway paymentGateway;

    @PostMapping("/payment")
    public void payment(@RequestBody PaymentProcessDto paymentProcessDto) throws BadRequestException {
        paymentGateway.processPayment(paymentProcessDto);
    }






}
