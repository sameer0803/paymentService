package com.payment.paymentService.factory;

import com.payment.paymentService.enums.PaymentTypes;
import com.payment.paymentService.service.RedisUtilityService;
import com.payment.paymentService.strategy.PaymentMethod;
import com.payment.paymentService.util.UPIPayment;

public class PaymentFactory {


    public static PaymentMethod getPayment(PaymentTypes type, double amount, String userId, String receiverId, String uuid, String data, RedisUtilityService redisService) {
     if (type == null)
     {
         throw new IllegalArgumentException("type cannot be null");
     }
        return switch (type) {
            case UPI ->
                    new UPIPayment(userId, receiverId, amount, uuid, data,redisService);
            case CARD -> throw new UnsupportedOperationException("Card payment is not implemented yet.");
            case WALLET -> throw new UnsupportedOperationException("Wallet payment is not implemented yet.");
        };
    }
}
