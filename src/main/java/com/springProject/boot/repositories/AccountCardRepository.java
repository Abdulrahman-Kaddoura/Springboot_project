package com.springProject.boot.repositories;

import com.springProject.boot.models.AccountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountCardRepository extends JpaRepository<AccountCard, UUID> {
}
