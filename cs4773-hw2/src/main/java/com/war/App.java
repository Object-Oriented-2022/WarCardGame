package com.war;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class App 
{
    
    public static void main( String[] args )
    {
        String fileName = "/cards.csv";
        if(checkIfEmpty(fileName) == true){ 
			System.exit(-1); 
		}
		Scanner filePointer = openFile(fileName);
		
		while(filePointer.hasNextLine()) {
			String line = filePointer.nextLine();
			//TO DO: PORT TO LL? AND THEN CREATE QUEUE OR STACK whichever fits game rules 
		}


		filePointer.close();
		
		System.exit(0); 
    }
    
    /**
	* Returns a boolean determining whether a file is empty or not.
	* 
	* @param  fileName 	input file path
	* @return      		true or false boolean determined by the file.
	*/
	public static boolean checkIfEmpty(String fileName) {
		File file = new File(fileName);
		if(file.length() == 0){
			System.err.println("No records found in data file");
			return true;
		}
		return false;
	}

	/**
	* Opens a file and returns a Scanner pointer to that file
	* 
	* @param  fileName 	input file path
	* @return      		Scanner pointer pointing to beginning of input file 
	* @throws			FileNotFound Exception
	*/
	public static Scanner openFile(String fileName) {
		Scanner filePointer = null;
		try {
			filePointer = new Scanner(new File(fileName));
		} catch (FileNotFoundException fileNotFound) {
			System.err.println(fileNotFound.getMessage());
			return null;
		}
		return filePointer;
	}

}
