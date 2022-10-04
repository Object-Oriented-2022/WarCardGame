package com.Player;

import com.card.Cards;
import junit.framework.TestCase;

import java.util.ArrayList;

import static com.war.DeckManipulation.createDeck;
import static com.war.DeckManipulation.halveDeck;

public class PlayerTest extends TestCase {

    public void testGetTopCard() {
        ArrayList<Cards> deck = createDeck(1);
        System.out.print("Top card of deck" + deck.get(0));
        Cards firstTopCard = deck.get(0);
        Player player1 = new BehaviorOne(halveDeck(deck).get(0), 1);
        System.out.println("\nPlayer 1 top card of deck "+player1.getTopCard());
        System.out.println(deck);
        System.out.println(player1.deck);
        assertEquals(firstTopCard, player1.getTopCard());
    }

    public void testRemoveCard() {
        ArrayList<Cards> deck = createDeck(1);
        Player player1 = new BehaviorOne(halveDeck(deck).get(0), 1);
        System.out.println("Deck for player 1 is 26 cards.");
        System.out.println(deck);
        Cards card = deck.get(0);
        player1.removeCard(card);
        System.out.println("Remove one card and deck should be 25 cards");
        System.out.println(deck);
        assertEquals(25, player1.deck.size());

    }

    public void testGetDeckSize() {
        ArrayList<Cards> deck = createDeck(1);
        Player player1 = new BehaviorOne(halveDeck(deck).get(0), 1);
        assertEquals(26, player1.getDeckSize());
        System.out.println("Deck size for player should be 26\n" + player1.deck);

    }
}