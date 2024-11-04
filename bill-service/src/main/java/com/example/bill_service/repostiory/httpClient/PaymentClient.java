package com.example.bill_service.repostiory.httpClient;

import com.example.bill_service.dto.response.PaymentClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "paymentClient", url = "${app.endpoint}")
public interface PaymentClient {

    @PostMapping(value = "/create", consumes = "application/x-www-form-urlencoded")
    Object callback(@RequestParam Map<String, ?> order);
}
