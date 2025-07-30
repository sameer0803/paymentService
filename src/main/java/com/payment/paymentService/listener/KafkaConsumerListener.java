package com.payment.paymentService.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @KafkaListener(topics="payment-data",groupId = "payment-group")
    public void listen(String record) {
 System.out.println("ecieved message from producer --->"+record);
    }

}
