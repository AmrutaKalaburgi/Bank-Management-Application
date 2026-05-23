package com.example.microservices.AccountService.client;

import com.example.microservices.AccountService.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "localhost:8081")
public interface Customerclient {

    @GetMapping("/customer/getcustomerById/{customerId}")
    CustomerDTO getCustomerInfo(@PathVariable Long customerId);
}
