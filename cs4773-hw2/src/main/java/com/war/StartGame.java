package com.war;

import com.Player.BehaviorOne;
import com.Player.BehaviorTwo;
import com.Player.Player;
import com.card.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.WarRules.WarOne.warOne;
import static com.WarRules.WarThree.warThree;
import static com.WarRules.WarTwo.warTwo;
import static com.card.Cards.createDeck;
import static com.war.DeckManipulation.halveDeck;
import static com.war.DeckManipulation.thirdDeck;

public class StartGame{
    static int gameVersion;
    static int maxNumRounds;
    static int seedNumber;
    static Player playerOne;
    static Player playerTwo;
    static List<ArrayList<Cards>> playerDecks;
    
    public static void startGame(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter game version 1, 2, or 3: ");
        gameVersion = in.nextInt();
        if(gameVersion == 1){
            System.out.print("Enter number of rounds to play: ");
            maxNumRounds = in.nextInt();
        }
        else{
            maxNumRounds = 0;
        }
        System.out.print("Seed for random number generator: ");
        seedNumber = in.nextInt();
        in.close();
        ArrayList<Cards> deck = createDeck(seedNumber);
        if(gameVersion == 3){
            playerDecks = thirdDeck(deck);
        } else {
            playerDecks = halveDeck(deck);
        }
    }

    public static void gVersion() {
        switch (gameVersion) {
            case 1:
                playerOne = new BehaviorOne(playerDecks.get(0), 1);
                playerTwo = new BehaviorOne(playerDecks.get(1), 2);
                warOne(maxNumRounds, playerOne, playerTwo);
                break;
            case 2:
                playerOne = new BehaviorTwo(playerDecks.get(0), 1);
                playerTwo = new BehaviorTwo(playerDecks.get(1), 2);
                warTwo(playerOne, playerTwo);
                System.out.println("v2 " + maxNumRounds + " " + seedNumber);
                break;
            case 3:
                playerOne = new BehaviorTwo(playerDecks.get(0), 1);
                playerTwo = new BehaviorTwo(playerDecks.get(1), 2);
                Player playerThree = new BehaviorTwo(playerDecks.get(2), 3);
                warThree(playerOne, playerTwo, playerThree);
                System.out.println("v3 " + maxNumRounds + " " + seedNumber);
                break;
            default:
        }
        System.exit(0);
    }
}
