package com.war;

import com.card.Cards;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.card.Suit.HEARTS;
import static com.card.Value.THREE;
import static com.war.DeckManipulation.*;

public class DeckManipulationTest extends TestCase {
    public void testCreateDeck() {
        ArrayList<Cards> deck = createDeck(1);
        assertNotNull(deck);
        System.out.println("Deck Size: " + deck.size());
        assertEquals(52, deck.size());
        System.out.println("Deck Top Card: " + deck.get(0));
        assertEquals(" plays "+THREE+" of "+HEARTS, deck.get(0).toString());
        System.out.println("Created Deck: ");
        System.out.println(deck);
    }

    public void testHalveDeck() {
        ArrayList<Cards> deck = createDeck(1);
        List<ArrayList<Cards>> splitDeck = halveDeck(deck);
        assertEquals(2, splitDeck.size());
        assertEquals(26, splitDeck.get(0).size());
        assertEquals(26, splitDeck.get(1).size());
        System.out.println("Decks Size: ");
        System.out.println(splitDeck.get(0).size());
        System.out.println(splitDeck.get(1).size());
        System.out.println("Decks Split: ");
        System.out.println(splitDeck.get(0));
        System.out.println(splitDeck.get(1));
    }

    public void testThirdDeck() {
        ArrayList<Cards> deck = createDeck(1);
        List<ArrayList<Cards>> splitDeck = thirdDeck(deck);
        assertEquals(3, splitDeck.size());
        assertEquals(17, splitDeck.get(0).size());
        assertEquals(18, splitDeck.get(1).size());
        assertEquals(17, splitDeck.get(2).size());
        assertTrue(splitDeck.get(0).size() + splitDeck.get(1).size() + splitDeck.get(2).size() == 52);
        System.out.println("Decks Size: ");
        System.out.println(splitDeck.get(0).size());
        System.out.println(splitDeck.get(1).size());
        System.out.println(splitDeck.get(2).size());
        System.out.println("Decks Split: ");
        System.out.println(splitDeck.get(0));
        System.out.println(splitDeck.get(1));
        System.out.println(splitDeck.get(2));
    }


}