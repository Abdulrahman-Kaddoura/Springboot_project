package com.springProject.boot.dtos.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountHolderRequestDTO {
    private String firstName;

    private String lastName;

    private Integer phoneNumber;

    private String email;

    private LocalDate dob;

    private String motherName;
}
