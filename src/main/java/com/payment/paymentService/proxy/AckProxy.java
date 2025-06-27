package com.payment.paymentService.proxy;

import com.payment.paymentService.dto.AcknowledgeDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient( name = "acknowledge-proxy", url = "http://localhost:8081")
public interface AckProxy {
@PostMapping("/acknowledge")
    public String acknowledge(@RequestBody AcknowledgeDto acknowledgeDto);

}
