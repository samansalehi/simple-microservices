package com.example.sam;

import com.example.sam.user.UserRepository;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.*;

/*
At TransferWise, we have debit cards all over the world. But, not all debit cards are the same. 
For example, they may have a different style, or different spending limits.
For example, this is a set of card types we might offer:

Mastercard Consumer EU:
- Available for customers in EU
- Only for consumer users
- Max 1 card per customer
- Card style: TransferWise Neon Green
- Max monthly limit: 10,000 EUR

Mastercard Business EU:
- Available for customers in EU
- Only for businesses users
- Max 5 card per customer
- Card style: TransferWise Neon Green + Business Blue
- Max monthly limit: 100,000 EUR

Mastercard Consumer USA:
- Available for customers in USA
- Only for consumer users
- Max 1 card per customer
- Card style: TransferWise Neon Green
- Max monthly limit: 15,000 USD

Exercise: We now want to build functionality to check if a customer can order a card. If a customer can order a card,
then we want to know for which type(s). If they can't order a card, then we want to know why not.

Some examples:
- A European consumer should be told that he can order the "Mastercard Consumer EU" card.
- A customer in Brazil should be told that we don't have cards in Brazil yet
- A business customer in US should be told that we don't offer business cards in the US
- A European consumer that already has too many cards should be told that he reached the maximum card limit
*/

enum Country {GB, EE, NL, US /*...*/};

enum UserType {CONSUMER, BUSINESS};

enum CardStyle {GREEN, GREEN_BLUE};

interface CardType {
    Collection<Country> getAvailableCountries();

    UserType getUserType();

    int getMaxCardsPerUser();

    CardStyle getCardStyle();

    String getName();
}

interface User {
    Long getUserId();

    Country getCountry();

    UserType getUserType();
}

interface CardRepository {
    void save(User user, CardType cardType);
    int allRegisterCardType(User user, CardType cardType);
}




class CardTypeProvider {
    private CardRepository cardRepository;
    private UserRepository userRepository;
    private static List<CardType> allCardTypes = new ArrayList<>();


    public List<CardType> getAvailableCardTypes(User user) {
        List<CardType> userAvailableCardType= new ArrayList<>();
        allCardTypes.forEach(cardType -> {
            if (cardType.getAvailableCountries().contains(user.getCountry())){
                userAvailableCardType.add(cardType);
            }
        });

        if (userAvailableCardType.size()>0)
        {
            return  userAvailableCardType;
        }else
        {
            throw new RuntimeException("There are not any Available Card For this Country:" + user.getCountry());
        }
    }

    public List<CardType> registerCard(User user,CardType cardType) {
        List<CardType> cardTypes =getAvailableCardTypes(user);

        if (cardTypes.contains(cardType)){
            checkMaxAllowedCardType(cardRepository.allRegisterCardType(user,cardType),cardType);
            cardRepository.save(user,cardType);
        }


    }

    private void checkMaxAllowedCardType(int allRegisterCardType, CardType cardType) {
        if (allRegisterCardType > cardType.getMaxCardsPerUser())
        {
            throw  new RuntimeException("Max Card Allowed Per User Reached  cardType:"+ cardType.getName());
        }
    }

    private boolean isCardAvailableInUserCountry(Country country, Collection<Country> availableCountries) {
    }


}