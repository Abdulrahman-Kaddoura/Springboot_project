package com.springProject.boot.Services;

import com.springProject.boot.Models.AccountHolder;
import com.springProject.boot.Repositories.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public AccountHolder createAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public AccountHolder getById(UUID id) throws Exception {
        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(id);
        if (accountHolder.isEmpty()) {
            throw new Exception();
        }
        return accountHolder.get();
    }

    public AccountHolder updateAccountHolder(AccountHolder accountHolder) throws Exception {
        getById(accountHolder.getId());
        return accountHolderRepository.save(accountHolder);
    }

    public void deleteById(UUID id) throws Exception{
        getById(id);
        accountHolderRepository.deleteById(id);
    }
}
