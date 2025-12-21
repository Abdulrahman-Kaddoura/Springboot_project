package com.springProject.boot.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@Audited
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private UUID id;

}
