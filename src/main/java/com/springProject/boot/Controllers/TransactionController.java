package com.springProject.boot.Controllers;

import com.springProject.boot.Services.TransactionService;
import com.springProject.boot.dtos.RequestDTOs.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction-controller")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/create-transaction")
    public void createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        transactionService.createTransaction(transactionRequestDTO);
    }
}
