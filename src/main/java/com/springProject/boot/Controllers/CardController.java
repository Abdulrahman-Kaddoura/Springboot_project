package com.springProject.boot.Controllers;

import com.springProject.boot.Models.Card;
import com.springProject.boot.Services.CardService;
import com.springProject.boot.dtos.CardResponseDTO;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create-card")
    public ResponseEntity<CardResponseDTO> createCard() {
        Card savedCard = cardService.createCard();
        CardResponseDTO cardResponseDTO = modelMapper.map(savedCard, CardResponseDTO.class);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.CREATED);
    }
}
