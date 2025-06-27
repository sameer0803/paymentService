package com.payment.paymentService.factory;

import com.payment.paymentService.enums.PaymentTypes;
import com.payment.paymentService.proxy.AckProxy;
import com.payment.paymentService.repository.PaymentProcessRepo;
import com.payment.paymentService.service.AckClientRestTemplate;
import com.payment.paymentService.service.RedisUtilityService;
import com.payment.paymentService.strategy.PaymentMethod;
import com.payment.paymentService.util.CardPayment;
import com.payment.paymentService.util.UPIPayment;
import com.payment.paymentService.util.Wallet;
import org.springframework.stereotype.Component;


public class PaymentFactory {

    public  static PaymentMethod getPayment(PaymentTypes type, double amount, String userId, String receiverId, String uuid, String data, RedisUtilityService redisService, AckProxy ackProxy, AckClientRestTemplate ackClientRestTemplate) {
     if (type == null)
     {
         throw new IllegalArgumentException("type cannot be null");
     }
        return switch (type) {
            case UPI ->
                    new UPIPayment(userId, receiverId, amount, uuid, data,redisService,ackProxy,ackClientRestTemplate);
            case CARD ->
                    new CardPayment(userId, receiverId, amount, uuid, data,redisService,ackProxy);
            case WALLET ->
                    new Wallet(userId, receiverId, amount, uuid, data);
        };
    }
}
