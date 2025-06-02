package com.payment.paymentService.proxy;

import com.payment.paymentService.dto.AcknowledgeDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8081")
public interface AckProxy {
@GetMapping("/acknowledge")
    public String acknowledge(@RequestBody AcknowledgeDto acknowledgeDto);

}
