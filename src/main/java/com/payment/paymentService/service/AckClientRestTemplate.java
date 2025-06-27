package com.payment.paymentService.service;

import com.payment.paymentService.dto.AcknowledgeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AckClientRestTemplate {

        private final  RestTemplate restTemplate;

    public AckClientRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public String acknowledgeCall(AcknowledgeDto acknowledgeDto){


        String url = "http://localhost:8081/acknowledge";
        HttpHeaders header= new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AcknowledgeDto> entity = new HttpEntity<>(acknowledgeDto, header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return  response.getBody();



    }
}
