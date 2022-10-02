package com.Player;

import com.card.Cards;

import java.util.ArrayList;

public interface PlayerBehavior {

    Cards getTopCard();

    Cards flipCards();

    void addCard(Cards card);

    void removeCard(Cards card);

    void won(ArrayList<Cards> deckWon);
    void lost();

    int getDeckSize();

    int getPoints();

}
