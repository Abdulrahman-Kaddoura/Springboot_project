package com.springProject.boot.Controllers;

import com.springProject.boot.Models.AccountHolder;
import com.springProject.boot.Services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account-holder")
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;


    @PostMapping("create-account-holder")
    public ResponseEntity<AccountHolder> createAccountHolder(@RequestBody AccountHolder accountHolder) {
        AccountHolder accountHolder1 = accountHolderService.createAccountHolder(accountHolder);
        return new ResponseEntity<>(accountHolder1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getById(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(accountHolderService.getById(id).getFirstName(), HttpStatus.OK);
    }
}
