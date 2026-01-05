package com.springProject.boot.services;

import com.springProject.boot.models.Card;
import com.springProject.boot.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public Card createCard() {
        Card card = new Card();

        //date
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 3);
        Date expiryDate = calendar.getTime();
        card.setExpiryDate(expiryDate);

        //cvv
        card.setCvv(String.valueOf(new Random().nextInt(900) + 100));

        //card number
        String bin = "12345678";
        Random random = new Random();
        StringBuilder randomDigits = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            randomDigits.append(random.nextInt(10));
        }
        String cardNumber = bin + randomDigits;
        card.setCardNumber(cardNumber);
        cardRepository.save(card);
        return card;
    }

    public Card findByCvv(String cvv) {
        Card card = cardRepository.findByCvv(cvv);
        return card;
    }
}
