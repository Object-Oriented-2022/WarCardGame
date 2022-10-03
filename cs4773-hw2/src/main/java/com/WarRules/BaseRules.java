package com.WarRules;

import com.Player.Player;
import com.card.Cards;
import com.war.EndCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseRules {

    private static ArrayList<Player> winners = new ArrayList<>();
    static Player winner = null;
    static EndCases endCase = null;
    static ArrayList<Cards> deckWon = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();

    static ArrayList<Player> warPlayers = new ArrayList<>();
    static ArrayList<Cards> warCards = new ArrayList<>();

    static List<Integer> deckSizes = new ArrayList<>();

    static boolean emptyDeckCheck(){
        for(Player player : players){
            if(player.getDeckSize() == 0) {
                endCase = EndCases.ONE_WINNER;
                return true;
            }
        }
        return false;
    }

    static void resetRound(){
        winner = null;
        deckWon.clear();
        warCards.clear();
        warPlayers.clear();
    }

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

    static void findWinner() {
        int biggestDeck = Collections.max(deckSizes);
        int indexOfDeck = deckSizes.indexOf(biggestDeck);
        deckSizes.remove(indexOfDeck);
        winner = players.get(indexOfDeck);
        winners.add(winner);
        if(deckSizes.contains(biggestDeck))
            findWinner();
    }

    static void endGame() {
        switch(endCase){
            case ONE_WINNER:
            case RAN_OUT:
                printWinner();
                break;
            case ROUNDS_END:
                System.out.println("Max number of rounds played");
                printWinner();
                break;
            case DRAW_CARDS:
                System.out.println("Draw: Players last cards were equal cards");
                printWinner();
                break;
            default:
                System.out.println("Some error occurred.");
        }
    }


    private static void printWinner() {
        if(winners.size() == 1) {
            System.out.print("Player " + winners.get(0).playerNum + " has won the game with ");
            System.out.println(winners.get(0).getPoints() + " cards");
        } else {
            for (Player player : winners) {
                System.out.print("Player " + player.playerNum + " has won the game with ");
                System.out.print(player.getPoints());
            }
        }
    }

    static void war() {
        System.out.println("*** WAR! ***");
        //for(int i = 0; i < players.size(); i++){
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
                System.out.println("Player " + player.playerNum + " does not have enough cards to play war!");
                deckWon.addAll(player.deck);
                player.deck.clear();
                if (!check)             //if check is equal to false, ive set it before
                    draw = true;        //there is a draw
                check = false;
                ablePlayers--;          //to handle when 3+ players
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
        //TODO PRINT CARDS PULLED INTO ARRAYLIST
        printCardsPulled(warCards);

        //determineCard
        //winner = compareCards();
        //winner.won(deckWon);
        if (warCards.get(0).rank < warCards.get(1).rank) {              //player 2 won
            //player two wins add all 6 cards to points pile
            warPlayers.get(1).won(deckWon);
            warPlayers.get(0).lost();

        } else if (warCards.get(0).rank > warCards.get(1).rank) {         //player 1 won
            warPlayers.get(0).won(deckWon);
            warPlayers.get(1).lost();
        } else { //equal again, start war over
            war();
        }
    }
}
