package com.war;

import com.Player.Player;
import com.card.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class WarVersions {

    static ArrayList<Player> winners = new ArrayList<>();
    static Player winner = null;
    public static EndCases endCase = null;
    private static ArrayList<Cards> deckWon = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();

    static ArrayList<Player> warPlayers = new ArrayList<>();
    static ArrayList<Cards> warCards = new ArrayList<>();

    static List<Integer> deckSizes = new ArrayList<>();

    private static void resetRound(){
        winner = null;
        deckWon.clear();
        warCards.clear();
        warPlayers.clear();
    }
    //TODO: MOVE CARDS INITIALIZATION HERE MAYBE
    public static void warOne (int maxRounds, Player playerOne, Player playerTwo) {
        int currentRound = 0;
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        while(currentRound < maxRounds && !emptyDeckCheck()){
            resetRound();
            warCards = pullCards(players);
            printCardsPulled(warCards);
            deckWon.addAll(warCards);
            //set winner
            //winner = compareCards();

            //winner.won(deckWon);
            if(warCards.get(0).rank < warCards.get(1).rank){              //player 2 won
                playerTwo.won(deckWon);
            }
            else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
                playerOne.won(deckWon);
            }
            else {
                warPlayers.addAll(players);
                war();
            }
            if(endCase != null)
                break;
            currentRound++;
        }
        if(endCase == null){
            if(currentRound == maxRounds){
                endCase = EndCases.ROUNDS_END;
            }
            else if(playerOne.getDeckSize() == 0 || playerOne.getDeckSize() == 0 ) {
                endCase = EndCases.ONE_WINNER;
            }
        }

        deckSizes.add(playerOne.getPoints());
        deckSizes.add(playerTwo.getPoints());
        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());
        findWinner();
        endGame();

    }

    private static boolean emptyDeckCheck(){
        for(Player player : players){
            if(player.getDeckSize() == 0) {
                endCase = EndCases.ONE_WINNER;
                return true;
            }
        }
        return false;
    }

    private static Player compareCards() {
        /*if(onesCard.rank < twosCard.rank){              //player 2 won
                playerTwo.won(deckWon);
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                playerOne.won(deckWon);
            }
            else {
                war(deckWon, new ArrayList<>(Arrays.asList(playerOne, playerTwo)));
            }*/

        Player winner = null;
        ArrayList<Integer> ranks = new ArrayList<>();
        for(Cards card: warCards){
            ranks.add(card.rank);
        }
        int highestCard = Collections.max(ranks);
        int frequency = Collections.frequency(ranks, highestCard);
        if(frequency == 1){
            int cardOwner = ranks.indexOf(highestCard);
            ranks.remove(cardOwner);
            winner = players.get(cardOwner);
        } else {
            for (int i = 0; i < ranks.size(); i++) {
                int cardRank = ranks.get(i);
                if (cardRank == highestCard) {
                    warPlayers.add(players.get(i));
                }
            }
            war();
        }
        return winner;

        /*
        int highestRank = -1;
        for(int i = 0; i < warCards.size(); i++){
            if(warCards.get(i).rank > highestRank){
                winner = players.get(i);
            } else if (warCards.get(i).rank == highestRank) {
                warPlayers.add(winner);
                warPlayers.add(players.get(i));
            }
        }
        if(warPlayers.size() >= 2)
            war();*/
        /*if(onesCard.rank < twosCard.rank){              //player 2 won
            playerTwo.won(deckWon);
        }
        else if(onesCard.rank > twosCard.rank){         //player 1 won
            playerOne.won(deckWon);
        }
        else {
            war(deckWon, new ArrayList<>(Arrays.asList(playerOne, playerTwo)));
        }*/
    }

    //TODO: adjust for war v3
    private static void endGame() {
        switch(endCase){
            case ONE_WINNER: //one player has won all the cards
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
            System.out.print(winners.get(0).getPoints() + " cards");
        } else {
            for (Player player : winners) {
                System.out.print("Player " + player.playerNum + " has won the game with ");
                System.out.print(player.getPoints());
            }
        }
    }

    private static void findWinner() {
        int biggestDeck = Collections.max(deckSizes);
        int indexOfDeck = deckSizes.indexOf(biggestDeck);
        deckSizes.remove(indexOfDeck);
        winner = players.get(indexOfDeck);
        winners.add(winner);
        if(deckSizes.contains(biggestDeck))
            findWinner();
    }

    /*Most descriptions of War are not clear about what happens if a player runs out of cards
during a war. There are at least two possibilities:
If you don't have enough cards to complete the war, you lose. If neither player has enough cards,
the one who runs out first loses. If both run out simultaneously, it's a draw. Example: Players A
and B both play sevens, so there is a war. Each player plays a card face down, but this is player
B's last card. Player A wins, since player B does not have enough cards to fight the war.

If you run out of cards during a war, your last card is turned face up and is used for all battles
in that war. If this happens to both players in a war and their last cards are equal, the game is a
draw. Example: Players A and B both play sevens, so there is a war. Player A plays a card face
down, but player B has only one card, so it must be played face up. It is a queen. Player A plays
a card face up and it is also a queen, so the war must continue. Player B's queen stays (B's last
card) while player A plays a card face down and one face up, which is a nine. Player B wins the
war and takes all these seven cards (the five cards that A played and the two cards that B
played) and the game continues normally.*/
    //check decks before proceeding with iterations
    //todo change to only do war for players who have war
    private static void war() {
        System.out.println("*** WAR! ***");
        //for(int i = 0; i < players.size(); i++){
        warCheck();
        if(endCase == null){
            warIteration();
        }

            //nested check loop maybe
            /* if(checkDecks(playerOne) && checkDecks(playerTwo)){    //both are fine
                System.out.println("Players have enough cards for war");
                warIteration(deckWon, playerOne, playerTwo);
            }
            else if(!checkDecks(playerOne)){             //player one < 2
                System.out.println("Player 1 does not have enough cards to play war!");
                giveCards(deckWon, playerTwo, playerOne);
                endCase = EndCases.RAN_OUT;
            }
            else if(!checkDecks(playerTwo)){             //player two < 2
                System.out.println("Player 2 does not have enough cards to play war!");
                giveCards(deckWon, playerTwo, playerOne);
                endCase = EndCases.RAN_OUT;
            }
            else if(!checkDecks(playerOne) && !checkDecks(playerTwo)){      //draw scenario, both 1 card
                giveCardsBack(deckWon, playerOne, playerTwo);
                endCase = EndCases.DRAW_CARDS;
            }*/
        //}
    }

//Todo if one player is out of cards make sure game doesnt end if other 2 players have cards still
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

/*
    private static void giveCards(ArrayList<Cards> deckWon, Player winner, Player loser) {
        deckWon.addAll(p1.deck);
        deckWon.addAll(p2.deck);
        winner.deck.clear();
        loser.deck.clear();
        winner.won(deckWon);
    }*/

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

    //check deck function
    private static boolean checkDecks(Player player) {
        return player.getDeckSize() > 2;
    }

    //begin war iterations
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

    private static ArrayList<Cards> pullCards(ArrayList<Player> players) {
        ArrayList<Cards> pulledCards = new ArrayList<>();
        for (Player player : players) {
            Cards card = player.flipCards();
            pulledCards.add(card);
        }
        return pulledCards;
    }

    private static void printCardsPulled(ArrayList<Cards> cards) {
        for (int i =0; i < cards.size(); i++) {
            System.out.println("Player " + (i+1) + " " + cards.get(i).toString());
        }
    }

    // A different version for two players. Cards that are won are placed in a separate points pile.
    // Cards are not recycled. The game ends after the players use the cards that were initially dealt to them.
    // The winner is the player with the most cards in his/her points pile at the end of the game.
    // If the players do not have enough cards to complete a war, that round is a draw, and they keep their cards.
    public static void warTwo (Player playerOne, Player playerTwo) {
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        while(!emptyDeckCheck()){
            resetRound();
            warCards = pullCards(players);
            printCardsPulled(warCards);
            deckWon.addAll(warCards);

            if(warCards.get(0).rank < warCards.get(1).rank){              //player 2 won
                playerTwo.won(deckWon);
                playerOne.lost();
            }
            else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
                playerOne.won(deckWon);
                playerTwo.lost();
            }
            else {
                System.out.println(warCards.get(0).rank + " is equal to " + warCards.get(1).rank);
                warPlayers.addAll(players);
                war();
            }

            /*winner = compareCards();

            winner.won(deckWon);
            if(winner.playerNum == playerOne.playerNum)
                playerTwo.lost();
            else
                playerOne.lost();

            if(endCase != null)
                break;
            */
            /*Cards onesCard = playerOne.flipCards();
            //Cards twosCard = playerTwo.flipCards();
            //ArrayList<Cards> cards = new ArrayList<>(Arrays.asList());
            //printCardsPulled(cards);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard));
            if(onesCard.rank < twosCard.rank){              //player 2 won
                playerTwo.won(deckWon);
                playerOne.lost();
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                playerOne.won(deckWon);
                playerTwo.lost();
            }
            else {
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank);
                war();
            }*/

        }

        if(endCase == null){
           if(playerOne.getDeckSize() == 0 || playerOne.getDeckSize() == 0) {
                endCase = EndCases.ONE_WINNER;
            }
        }

        //List<Integer> deckSizes = new ArrayList<>(Arrays.asList(playerOne.getPoints(), playerTwo.getPoints()));
        deckSizes.add(playerOne.getPoints());
        deckSizes.add(playerTwo.getPoints());
        findWinner();
        endGame();

        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());

    }
    //3) The three-player war game, but with one change. Cards that are won are placed in a separate points pile.
    // The winner is the player with most cards his/her in points pile at end of game.
    // If players do not have enough cards to complete a war, use the first listed possibility for ending the game.
    //war(deckWon, new ArrayList<>(Arrays.asList(playerOne, playerTwo)));

    public static void warThree (ArrayList<Player> players) {
        while(players.get(0).getDeckSize() > 0 && players.get(1).getDeckSize() > 0 && players.get(2).getDeckSize() > 0){
            resetRound();
            deckWon.clear();
            Cards onesCard = players.get(0).flipCards();
            Cards twosCard = players.get(1).flipCards();
            Cards threesCard = players.get(2).flipCards();
            ArrayList<Cards> cards = new ArrayList<>(Arrays.asList(onesCard, twosCard, threesCard));

            printCardsPulled(cards);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard, threesCard));
            if(onesCard.rank < twosCard.rank && threesCard.rank < twosCard.rank){              //player 2 won
                players.get(1).won(deckWon);
                players.get(0).lost();
                players.get(2).lost();
            } else if(onesCard.rank > twosCard.rank && onesCard.rank > threesCard.rank){         //player 1 won
                players.get(0).won(deckWon);
                players.get(1).lost();
                players.get(2).lost();
            } else if (onesCard.rank < threesCard.rank && threesCard.rank > twosCard.rank){              //player 2 won
                players.get(2).won(deckWon);
                players.get(0).lost();
                players.get(1).lost();
            } else {
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank + " is equal to " + threesCard.rank);
                //war(deckWon, new ArrayList<>(Arrays.asList(players.get(0), players.get(1), players.get(2))));
            }
            if(endCase != null)
                break;
        }

        //List<Integer> deckSizes = new ArrayList<>(Arrays.asList(players.get(0).getPoints(), players.get(1).getPoints(), players.get(2).getDeckSize()));
        deckSizes.add(players.get(0).getPoints());
        deckSizes.add(players.get(1).getPoints());
        deckSizes.add(players.get(2).getPoints());
        endGame();

        System.out.println(players.get(0).deck.toString());
        System.out.println(players.get(1).deck.toString());
        System.out.println(players.get(2).deck.toString());

    }

}
