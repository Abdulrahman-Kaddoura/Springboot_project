package com.springProject.boot.Controllers;

import com.springProject.boot.Models.Card;
import com.springProject.boot.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/create-card")
    public ResponseEntity<Card> createCard() {
        return new ResponseEntity<>(cardService.createCard(), HttpStatus.CREATED);
    }
}
