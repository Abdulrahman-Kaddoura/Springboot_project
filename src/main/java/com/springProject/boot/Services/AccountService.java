package com.springProject.boot.Services;

import com.springProject.boot.Models.Account;
import com.springProject.boot.Models.AccountHolder;
import com.springProject.boot.Repositories.AccountHolderRepository;
import com.springProject.boot.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Account createAccount(Account account) throws Exception {
        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(account.getAccountHolder().getId());

        if (accountHolder.isPresent()) {
            account.setAccountHolder(accountHolder.get());
            return accountRepository.save(account);
        } else {
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
}
