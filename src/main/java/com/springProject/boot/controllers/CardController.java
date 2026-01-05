package com.springProject.boot.controllers;

import com.springProject.boot.models.Card;
import com.springProject.boot.services.CardService;
import com.springProject.boot.dtos.ResponseDTOs.CardResponseDTO;
import com.springProject.boot.dtos.RequestDTOs.FindByCvvRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.togglz.core.manager.FeatureManager;

import static com.springProject.boot.togglz.Features.CREATE_CARD;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FeatureManager featureManager;

    @PostMapping("/create-card")
    public ResponseEntity<CardResponseDTO> createCard() {
        if (featureManager.isActive(CREATE_CARD)) {
            Card savedCard = cardService.createCard();
            CardResponseDTO cardResponseDTO = modelMapper.map(savedCard, CardResponseDTO.class);
            return new ResponseEntity<>(cardResponseDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new CardResponseDTO(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/find-card-by-cvv")
    public ResponseEntity<CardResponseDTO> findByCvv(@RequestBody FindByCvvRequestDTO findByCvvRequestDTO) {
        Card card = cardService.findByCvv(findByCvvRequestDTO.getCvv());
        CardResponseDTO cardResponseDTO = modelMapper.map(card, CardResponseDTO.class);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.FOUND);
    }
}
