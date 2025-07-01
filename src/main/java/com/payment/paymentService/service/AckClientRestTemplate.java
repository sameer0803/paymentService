package com.payment.paymentService.service;

import com.payment.paymentService.dto.AcknowledgeDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AckClientRestTemplate {

        private final  RestTemplate restTemplate;

    public AckClientRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


@CircuitBreaker(name="ackService",fallbackMethod = "acknowledgeFallback")
@Retry(name ="ackService")
    public String acknowledgeCall(AcknowledgeDto acknowledgeDto){
    System.out.println("Inside ack call");
        String url = "http://ACKNOWLEDGMENT/acknowledge";
        HttpHeaders header= new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AcknowledgeDto> entity = new HttpEntity<>(acknowledgeDto, header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return  response.getBody();

    }

    public String acknowledgeFallback(Throwable throwable)
    {
        return "Service Not available"+throwable.getMessage();
    }
}
