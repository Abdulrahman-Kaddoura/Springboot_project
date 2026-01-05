package com.springProject.boot.services;

import com.springProject.boot.models.Account;
import com.springProject.boot.models.AccountCard;
import com.springProject.boot.models.Card;
import com.springProject.boot.repositories.AccountCardRepository;
import com.springProject.boot.repositories.AccountRepository;
import com.springProject.boot.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountCardService {
    @Autowired
    AccountCardRepository accountCardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    public UUID linkAccountCard(UUID accountId, UUID cardId) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        Optional<Card> card = cardRepository.findById(cardId);
        if (account.isEmpty() || card.isEmpty()) {
            throw new Exception();
        }
        AccountCard accountCard = new AccountCard();
        accountCard.setAccount(account.get());
        accountCard.setCard(card.get());
        AccountCard savedAccountCard = accountCardRepository.save(accountCard);
        return savedAccountCard.getId();
    }
}
