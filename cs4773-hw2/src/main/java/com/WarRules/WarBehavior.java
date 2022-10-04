package com.WarRules;

import com.Player.Player;
import com.card.Cards;

import java.util.ArrayList;
import java.util.List;

/**
 * War holds variables and base war interactions.
 */
public class WarBehavior {
    static Player winner = null;
    static EndCases endCase = null;
    static ArrayList<Cards> deckWon = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Player> viablePlayers = new ArrayList<>();
    static ArrayList<Player> warPlayers = new ArrayList<>();
    static ArrayList<Cards> warCards = new ArrayList<>();
    static List<Integer> deckSizes = new ArrayList<>();

    static ArrayList<Cards> pullCards(ArrayList<Player> players) {
        ArrayList<Cards> pulledCards = new ArrayList<>();
        for (Player player : players) {
            Cards card = player.flipCards();
            pulledCards.add(card);
        }
        return pulledCards;
    }

    static void printCardsPulled(ArrayList<Cards> cards) {
        for (int i =0; i < cards.size(); i++) {
            System.out.println("Player " + (i+1) + " " + cards.get(i).toString());
        }
    }

    static void war() {
        System.out.println("*** WAR! ***");
        warCheck();
        if(endCase == null){
            warIteration();
        }
    }

    private static void warCheck() {
        int ablePlayers = warPlayers.size();
        boolean check = true;
        boolean draw = false;
        for (Player player : warPlayers) {
            if (!checkDecks(player)) {
                System.out.println("Player "+player.playerNum+" does not have enough cards to play war!");
                deckWon.addAll(player.deck);
                player.deck.clear();
                if (!check)
                    draw = true;
                check = false;
                ablePlayers--;
            }
        }
        if(draw) {
            giveCardsBack();
            endCase = EndCases.DRAW_CARDS;
        }
        else if (!check && ablePlayers <= 1) {
            endCase = EndCases.RAN_OUT;
        }
    }

    private static boolean checkDecks(Player player) {
        return player.getDeckSize() > 2;
    }

    private static void giveCardsBack() {
        int currPlayer = 0;
        for (Cards card : deckWon) {
            if (currPlayer > (warPlayers.size()-1))
                currPlayer = 0;
            warPlayers.get(currPlayer).addCard(card);
            currPlayer++;
        }
        deckWon.clear();
        warPlayers.forEach((player) -> {
            player.won(player.deck);
            player.deck.clear();
        });
    }

    private static void warIteration() {
        ArrayList<Cards> faceDownCards = pullCards(warPlayers);
        ArrayList<Cards> warCards = pullCards(warPlayers);

        deckWon.addAll(faceDownCards);
        deckWon.addAll(warCards);
        printCardsPulled(warCards);
        compareCards(warCards);
    }

    private static void compareCards(ArrayList<Cards> warCards){
        if (warCards.get(0).rank < warCards.get(1).rank) {
            warPlayers.get(1).won(deckWon);
            warPlayers.get(0).lost();

        } else if (warCards.get(0).rank > warCards.get(1).rank) {
            warPlayers.get(0).won(deckWon);
            warPlayers.get(1).lost();
        } else {
            war();
        }
    }
}
