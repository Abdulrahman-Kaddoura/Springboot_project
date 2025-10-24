package com.springProject.boot.Services;

import com.springProject.boot.Models.Account;
import com.springProject.boot.Models.AccountCard;
import com.springProject.boot.Models.Card;
import com.springProject.boot.Repositories.AccountCardRepository;
import com.springProject.boot.Repositories.AccountRepository;
import com.springProject.boot.Repositories.CardRepository;
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
