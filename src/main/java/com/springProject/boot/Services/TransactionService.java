package com.springProject.boot.Services;

import com.springProject.boot.Models.Account;
import com.springProject.boot.Models.AccountCard;
import com.springProject.boot.Models.Card;
import com.springProject.boot.Models.Transaction;
import com.springProject.boot.Repositories.AccountCardRepository;
import com.springProject.boot.Repositories.AccountRepository;
import com.springProject.boot.Repositories.TransactionRepository;
import com.springProject.boot.dtos.RequestDTOs.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountCardRepository accountCardRepository;

    @Autowired
    AccountRepository accountRepository;

    public void createTransaction(TransactionRequestDTO transactionRequestDTO) throws Exception {
        Optional<AccountCard> accountCard = accountCardRepository.findById(transactionRequestDTO.getAccountCard());
        Card card = accountCard.get().getCard();
        if (card.getExpiryDate().before(new Date())) {
            log.error("card expired");
            throw new Exception();
        }
        Account account = accountCard.get().getAccount();
        if (transactionRequestDTO.getTransactionType().equals("D")) {
            if (account.getBalance().subtract(transactionRequestDTO.getAmount()).intValue() < 0) {
                throw new Exception();
            }
        }

        UUID transactionId = UUID.randomUUID();

        if (transactionRequestDTO.getTransactionType().equals("C")) {
            account.setBalance(account.getBalance().add(transactionRequestDTO.getAmount()));
        } else {
            account.setBalance(account.getBalance().subtract(transactionRequestDTO.getAmount()));
        }
        accountRepository.save(account);
        Transaction transaction = modelMapper.map(transactionRequestDTO, Transaction.class);
        transaction.setId(transactionId);
        transaction.setCard(card);
        transactionRepository.save(transaction);
    }

    public int findTransactionCountByCard(UUID cardId) {
        return transactionRepository.findByCardId(cardId).size();
    }
}
