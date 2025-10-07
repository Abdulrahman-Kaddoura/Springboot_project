package com.springProject.boot.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private UUID id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @ManyToMany
    private List<Account> accountList;
}
