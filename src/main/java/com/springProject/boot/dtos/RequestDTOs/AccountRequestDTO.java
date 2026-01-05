package com.springProject.boot.dtos.RequestDTOs;

import com.springProject.boot.models.AccountHolder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDTO {
    private BigDecimal balance;

    private String status;

    private AccountHolder accountHolder;
}
