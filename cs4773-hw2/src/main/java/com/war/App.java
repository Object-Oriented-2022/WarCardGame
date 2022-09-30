package com.war;

import java.io.*;
import java.util.*;

import static com.war.Cards.createDeck;
import static com.war.WarVersions.warOne;
import static com.war.WarVersions.warTwo;

public class App
{

	//Your program will use its command line arguments to determine which version to play.
	// The first command line argument will be either 1, 2, or 3 indicating the version to play.
	// If version 1 is selected, then the second command line argument will be the maximum number of rounds to play.
	// For each version, the last command line argument is a number that is to be used as a seed for your
	// random number generator. This will allow a game to be replayed with the same results.
    
    public static void main( String[] args ) throws IOException {
		Scanner in = new Scanner(System.in);
		int gameVersion = 0;
		int maxNumRounds = 0;
		int seedNumber = 0;

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

		switch(gameVersion){
			case 1:
				warOne(maxNumRounds, deck);
				break;
			case 2:
				warTwo(deck);
				System.out.println("v2 " + maxNumRounds + " " + seedNumber);
				break;
			case 3:
				System.out.println("v3 " + maxNumRounds + " " + seedNumber);
				break;
			default:

		}

		//TEST PRINT STATEMENTS
		/*
		System.out.println(Cards.createDeck());
		System.out.print("Game Version chosen: "+gameVersion);
		System.out.print("\nNumber of rounds: "+maxNumRounds);
		System.out.print("\nSeed number: "+seedNumber);
		*/
		System.exit(0);
	}

}
