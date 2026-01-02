package com.springProject.boot.Feign;

import com.springProject.boot.dtos.CheckFraudDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "fraud", url = "http://localhost:8081")
public interface FraudFeignClient {
    @PostMapping("fraud-controller/check-fraud")
    ResponseEntity<Boolean> checkFraud(@RequestBody CheckFraudDTO checkFraudDTO);
}