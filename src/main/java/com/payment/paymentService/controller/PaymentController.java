package com.payment.paymentService.controller;

import com.payment.paymentService.dto.PaymentProcessDto;
import com.payment.paymentService.service.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentGateway paymentGateway;

    @PostMapping("/payment")
    public void payment(@RequestBody PaymentProcessDto paymentProcessDto) throws BadRequestException {
        paymentGateway.processPayment(paymentProcessDto);
    }




}
