package com.springProject.boot.dtos.ResponseDTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class TransactionResponseDTO {
    private UUID transactionId;
    private BigDecimal amount;
    private Timestamp transactionDate;
    private String transactionType;
}
