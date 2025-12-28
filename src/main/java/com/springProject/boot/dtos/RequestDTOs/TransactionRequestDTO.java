package com.springProject.boot.dtos.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionRequestDTO {
    private BigDecimal amount;
    private String transactionType;
    private UUID accountCard;
}
