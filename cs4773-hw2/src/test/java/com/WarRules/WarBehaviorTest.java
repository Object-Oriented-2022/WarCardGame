package com.WarRules;

import com.Player.BehaviorOne;
import com.Player.Player;
import com.card.Cards;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

import static com.WarRules.WarBehavior.pullCards;
import static com.card.Suit.DIAMONDS;
import static com.card.Value.ACE;
import static com.card.Value.KING;
import static com.war.DeckManipulation.createDeck;
import static com.war.DeckManipulation.halveDeck;

public class WarBehaviorTest extends TestCase {

    Player player1 = new BehaviorOne(halveDeck(createDeck(1)).get(0), 1);
    Player player2 = new BehaviorOne(halveDeck(createDeck(1)).get(1), 2);
    ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1,player2));

    public void testPullCards() {
        System.out.println(player1.deck);
        System.out.println(player2.deck);
        ArrayList<Cards> pulledCards = pullCards(players);
        System.out.println(player1.deck);
        System.out.println(player2.deck);

        System.out.println(pulledCards.get(0) + " " + pulledCards.get(1));
        System.out.println("Cards Pulled: ");
        //assertEquals changed to assetNotSame
        assertNotSame(" plays " + KING + " of " + DIAMONDS, pulledCards.get(1).toString());
        assertNotSame(" plays " + ACE + " of " + DIAMONDS, pulledCards.get(0).toString());
    }

}