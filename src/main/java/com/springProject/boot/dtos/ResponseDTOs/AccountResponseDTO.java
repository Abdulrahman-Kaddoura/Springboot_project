package com.springProject.boot.dtos.ResponseDTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AccountResponseDTO {

    private UUID id;

    private BigDecimal balance;

    private String status;

    private UUID accountHolderId;
}
