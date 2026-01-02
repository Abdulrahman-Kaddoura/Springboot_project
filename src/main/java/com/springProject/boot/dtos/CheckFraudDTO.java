package com.springProject.boot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CheckFraudDTO {
    private BigDecimal transactionAmount;
    private UUID transactionId;
    private UUID cardId;
}
