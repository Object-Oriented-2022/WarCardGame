package com.war;

import java.util.ArrayList;

public class Cards {
    private Suit suit;
    private Value value;
    private int rank;
    private static ArrayList<Cards> cardDeck = new ArrayList<>();

    public Cards(Suit suit, Value value, int rank) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }
    static ArrayList<Cards> createDeck(){
        for(int i =1; i < 15; i++){
            switch (i){
                case 1:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.HEARTS, value, i++));

                    }
                    i = 1;
                case 15:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.DIAMONDS, value, i++));

                    }
                    i = 1;
                case 30:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.SPADES, value, i++));
                    }
                    i = 1;
                case 45:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.CLUBS, value, i++));

                    }
                    i = 1;
            }
        }
        return cardDeck;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "suit=" + suit +
                ", value=" + value +
                ", rank=" + rank +
                '}';
    }
}
