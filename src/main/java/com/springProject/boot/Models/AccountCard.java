package com.springProject.boot.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account_card")
@Getter
@Setter
public class AccountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_card_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
