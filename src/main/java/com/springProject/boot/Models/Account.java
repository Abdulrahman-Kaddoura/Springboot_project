package com.springProject.boot.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private UUID id;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @ManyToMany
    private List<Card> cardList;

}
