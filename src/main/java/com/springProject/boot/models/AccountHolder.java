package com.springProject.boot.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "account_holder", schema = "cms")
@Getter
@Setter
@Audited
public class AccountHolder {

    @Column(name = "account_holder_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "mother_name")
    private String motherName;

    @OneToMany(mappedBy = "accountHolder")
    private List<Account> accounts = new ArrayList<Account>();
}
