package com.springProject.boot.services;

import com.springProject.boot.models.Account;
import com.springProject.boot.models.AccountHolder;
import com.springProject.boot.repositories.AccountHolderRepository;
import com.springProject.boot.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Account createAccount(Account account) throws Exception {
        log.trace("creating account {}", account);
        log.debug("getting account holder by id {}", account.getAccountHolder().getId());
        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(account.getAccountHolder().getId());
        log.debug("account holder is retreived of id {}", accountHolder.get().getId());

        if (accountHolder.isPresent()) {
            account.setAccountHolder(accountHolder.get());
            return accountRepository.save(account);
        } else {
            log.error("account holder is not present");
            throw new Exception();
        }
    }

    public Account updateAccount(Account account, UUID id) throws Exception {
        Optional<Account> dbAccount = accountRepository.findById(id);
        if (dbAccount.isPresent()) {
            account.setId(id);
            return accountRepository.save(account);
        } else {
            throw new Exception();
        }
    }

    public BigDecimal modifyBalance(BigDecimal balance, UUID id) throws Exception {
        Optional<Account> dbAccount = accountRepository.findById(id);
        if (dbAccount.isPresent()) {
            dbAccount.get().setBalance(balance);
            accountRepository.save(dbAccount.get());
            return dbAccount.get().getBalance();
        } else {
            throw new Exception();
        }
    }
}
