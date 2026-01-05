package com.springProject.boot.controllers;

import com.springProject.boot.services.AccountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/account-card")
public class AccountCardController {
    @Autowired
    AccountCardService accountCardService;

    @PostMapping("/link-account-card/{accountId}/{cardId}")
    public ResponseEntity<UUID> linkAccountCard(@PathVariable UUID accountId, @PathVariable UUID cardId) throws Exception {
        return new ResponseEntity<>(accountCardService.linkAccountCard(accountId,cardId), HttpStatus.CREATED);
    }
}
