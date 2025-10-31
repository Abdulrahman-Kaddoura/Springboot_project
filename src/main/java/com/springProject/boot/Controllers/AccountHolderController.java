package com.springProject.boot.Controllers;

import com.springProject.boot.Models.AccountHolder;
import com.springProject.boot.Services.AccountHolderService;
import com.springProject.boot.dtos.AccountHolderRequestDTO;
import com.springProject.boot.dtos.AccountHolderResponseDTO;
import com.springProject.boot.dtos.AccountResponseDTO;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create-account-holder")
    public ResponseEntity<AccountHolderResponseDTO> createAccountHolder(@RequestBody AccountHolderRequestDTO accountHolderRequestDTO) {
        AccountHolder accountHolder = modelMapper.map(accountHolderRequestDTO, AccountHolder.class);
        AccountHolder savedAccountHolder = accountHolderService.createAccountHolder(accountHolder);
        AccountHolderResponseDTO accountResponseDTO = modelMapper.map(savedAccountHolder, AccountHolderResponseDTO.class);
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getById(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(accountHolderService.getById(id).getFirstName(), HttpStatus.OK);
    }
}
