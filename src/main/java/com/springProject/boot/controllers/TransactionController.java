package com.springProject.boot.controllers;

import com.springProject.boot.feign.FraudFeignClient;
import com.springProject.boot.services.TransactionService;
import com.springProject.boot.dtos.CheckFraudDTO;
import com.springProject.boot.dtos.RequestDTOs.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("transaction-controller")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    FraudFeignClient fraudFeignClient;

    @PostMapping("/create-transaction")
    public ResponseEntity<UUID> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        UUID transactionId = transactionService.createTransaction(transactionRequestDTO);
        CheckFraudDTO checkFraudDTO = new CheckFraudDTO();
        checkFraudDTO.setCardId(transactionRequestDTO.getAccountCard());
        checkFraudDTO.setTransactionAmount(transactionRequestDTO.getAmount());
        checkFraudDTO.setTransactionId(transactionId);
        try {
            fraudFeignClient.checkFraud(checkFraudDTO);
        } catch (Exception e) {
            System.out.println("Error when checking fraud, check if fraud is running");
        }

        return new ResponseEntity<>(transactionId, HttpStatus.CREATED);
    }

    @GetMapping("/find-transaction-card/{cardId}")
    public ResponseEntity<Integer> findTransactionByCard(@PathVariable UUID cardId) {
        return new ResponseEntity<>(transactionService.findTransactionCountByCard(cardId), HttpStatus.ACCEPTED);
    }
}
