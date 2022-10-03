package com.war;

import com.Player.BehaviorOne;
import com.Player.BehaviorTwo;
import com.Player.Player;
import com.card.Cards;

import java.io.*;
import java.util.*;

import static com.WarRules.WarOne.WarOne;
import static com.WarRules.WarThree.WarThree;
import static com.WarRules.WarTwo.WarTwo;
import static com.card.Cards.createDeck;
import static com.war.DeckManipulation.halveDeck;
import static com.war.DeckManipulation.thirdDeck;

public class App
{

	//Your program will use its command line arguments to determine which version to play.
	// The first command line argument will be either 1, 2, or 3 indicating the version to play.
	// If version 1 is selected, then the second command line argument will be the maximum number of rounds to play.
	// For each version, the last command line argument is a number that is to be used as a seed for your
	// random number generator. This will allow a game to be replayed with the same results.
    
    public static void main( String[] args ) throws IOException {
		Scanner in = new Scanner(System.in);
		int gameVersion;
		int maxNumRounds;
		int seedNumber;

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
		List<ArrayList<Cards>> playerDecks = null;
		ArrayList<Cards> deck = createDeck(seedNumber);
		if(gameVersion == 3){
			playerDecks = thirdDeck(deck);
		} else {
			playerDecks = halveDeck(deck);
		}

		Player playerOne;
		Player playerTwo;
		switch(gameVersion){
			case 1:
				playerOne = new BehaviorOne(playerDecks.get(0), 1);
				playerTwo= new BehaviorOne(playerDecks.get(1), 2);
				WarOne(maxNumRounds, playerOne, playerTwo);
				break;
			case 2:
				playerOne = new BehaviorTwo(playerDecks.get(0), 1);
				playerTwo= new BehaviorTwo(playerDecks.get(1), 2);
				WarTwo(playerOne, playerTwo);
				System.out.println("v2 " + maxNumRounds + " " + seedNumber);
				break;
			case 3:
				playerOne = new BehaviorTwo(playerDecks.get(0), 1);
				playerTwo= new BehaviorTwo(playerDecks.get(1), 2);
				Player playerThree = new BehaviorTwo(playerDecks.get(2), 3);
				WarThree(playerOne, playerTwo, playerThree);
				System.out.println("v3 " + maxNumRounds + " " + seedNumber);
				break;
			default:

		}
		System.exit(0);
	}

}
