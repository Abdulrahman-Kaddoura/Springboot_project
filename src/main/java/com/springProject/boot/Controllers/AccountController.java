package com.springProject.boot.Controllers;

import com.springProject.boot.Models.Account;
import com.springProject.boot.Models.AccountHolder;
import com.springProject.boot.Services.AccountService;
import com.springProject.boot.dtos.AccountRequestDTO;
import com.springProject.boot.dtos.AccountResponseDTO;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create-account")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws Exception {
        Account account = modelMapper.map(accountRequestDTO, Account.class);
        Account savedAccount = accountService.createAccount(account);
        AccountResponseDTO accountResponseDTO = modelMapper.map(savedAccount, AccountResponseDTO.class);
        accountResponseDTO.setAccountHolderId(savedAccount.getAccountHolder().getId());
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

}
