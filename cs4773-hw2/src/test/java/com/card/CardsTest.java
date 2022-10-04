package com.card;

import junit.framework.TestCase;

import static com.card.Suit.SPADES;
import static com.card.Value.THREE;

public class CardsTest extends TestCase {
    Cards card;

    public void testAssignment() {
        card = new Cards(SPADES, THREE, 4);
        assertEquals(" plays "+THREE+" of "+SPADES, card.toString());
        System.out.println(card.toString());
    }
}