package com.springProject.boot.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "account_holder")
@Getter
@Setter
public class AccountHolder {

    @Column(name = "account_holder_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "mother_name")
    private String motherName;

    @OneToMany(mappedBy = "accountHolder")
    private List<Account> accounts = new ArrayList<Account>();
}
