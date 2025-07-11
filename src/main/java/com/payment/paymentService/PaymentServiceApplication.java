package com.payment.paymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;


@EnableFeignClients
@EnableDiscoveryClient
@EnableRetry
@SpringBootApplication
public class PaymentServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
