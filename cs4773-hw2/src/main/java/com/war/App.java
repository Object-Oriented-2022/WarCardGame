package com.war;

import java.io.*;
import java.util.*;
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
		System.out.print("Enter number of rounds to play: ");
		maxNumRounds = in.nextInt();
		System.out.print("Seed for random number generator: ");
		maxNumRounds = in.nextInt();
		in.close();
		System.out.print("Game Version chosen: "+gameVersion);
		System.out.print("\nNumber of rounds: "+maxNumRounds);
		System.out.print("\nSeed number: "+seedNumber);
		System.out.println(Cards.createDeck());
		System.exit(0);
	}

}
