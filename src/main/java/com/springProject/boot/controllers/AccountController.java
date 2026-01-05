package com.springProject.boot.controllers;

import com.springProject.boot.models.Account;
import com.springProject.boot.services.AccountService;
import com.springProject.boot.dtos.RequestDTOs.AccountRequestDTO;
import com.springProject.boot.dtos.ResponseDTOs.AccountResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.togglz.core.manager.FeatureManager;

import java.math.BigDecimal;
import java.util.UUID;

import static com.springProject.boot.togglz.Features.CREATE_ACCOUNT;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FeatureManager featureManager;

    @PostMapping("/create-account")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws Exception {
        if (featureManager.isActive(CREATE_ACCOUNT)) {
            Account account = modelMapper.map(accountRequestDTO, Account.class);
            Account savedAccount = accountService.createAccount(account);
            AccountResponseDTO accountResponseDTO = modelMapper.map(savedAccount, AccountResponseDTO.class);
            accountResponseDTO.setAccountHolderId(savedAccount.getAccountHolder().getId());
            return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new AccountResponseDTO(), HttpStatus.FORBIDDEN);
        }
    }

    @PatchMapping("/patch-account/{id}/{balance}")
    public ResponseEntity<BigDecimal> patchAccount(@PathVariable UUID id, @PathVariable BigDecimal balance) throws Exception {
        BigDecimal newBalance = accountService.modifyBalance(balance, id);
        return new ResponseEntity<>(newBalance, HttpStatus.ACCEPTED);
    }

}
