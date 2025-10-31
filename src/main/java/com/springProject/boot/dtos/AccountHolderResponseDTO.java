package com.springProject.boot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccountHolderResponseDTO {
    private UUID id;

    private String firstName;

    private String lastName;

    private Integer phoneNumber;

    private String email;

    private LocalDate dob;

    private String motherName;
}
