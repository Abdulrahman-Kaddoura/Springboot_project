package com.springProject.boot.dtos.ResponseDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CardResponseDTO {
    private UUID id;

    private String cvv;

    private String cardNumber;

    private Date expiryDate;
}
