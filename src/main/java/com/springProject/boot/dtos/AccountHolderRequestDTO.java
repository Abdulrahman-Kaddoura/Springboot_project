package com.springProject.boot.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.UUID;

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
