package com.springProject.boot.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction", schema = "cms")
@Getter
@Setter
@Audited
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private UUID id;

    @Column(name = "transaction_amount")
    @DecimalMin(value = "0.01", message = "Transaction amount must be greater than 0")
    private BigDecimal transactionAmount;

    @Column(name = "transaction_date")
    private Timestamp transactionDate = new Timestamp(System.currentTimeMillis());

    @NotNull(message = "Transaction type is mandatory")
    @Column(name = "transaction_type")
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

}
