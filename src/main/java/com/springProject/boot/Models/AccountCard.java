package com.springProject.boot.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account_card", schema = "cms")
@Getter
@Setter
@Audited
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
