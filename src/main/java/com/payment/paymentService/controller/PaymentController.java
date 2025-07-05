package com.payment.paymentService.controller;

import com.payment.paymentService.dto.PaymentProcessDto;
import com.payment.paymentService.service.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PaymentController {


    private final PaymentGateway paymentGateway;

    @PostMapping("/payment")
    public void payment(@RequestBody PaymentProcessDto  paymentProcessDto) throws BadRequestException {
        try {
            paymentGateway.processPayment(paymentProcessDto);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

    }






}
