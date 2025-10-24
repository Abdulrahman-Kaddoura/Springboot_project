package com.springProject.boot.Controllers;

import com.springProject.boot.Models.Account;
import com.springProject.boot.Models.AccountHolder;
import com.springProject.boot.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<UUID> createAccount(@RequestBody Account account) throws Exception {
        return new ResponseEntity<>(accountService.createAccount(account).getId(), HttpStatus.CREATED);
    }

}
