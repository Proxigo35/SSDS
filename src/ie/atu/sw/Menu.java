package ie.atu.sw;

import java.util.Scanner;
import java.io.File;

public class Menu {
	
	private static File queryFile;
	
	protected static void setQueryFile(File file) {
		queryFile = file;
	}

	private static File subjectFile;
	
	protected static void setSubjectFile(File file) {
		subjectFile = file;
	}
	
	private static File outputFile;
	
	protected static void setOutputFile(File file) {
		outputFile = file;
	}

	protected static File getOutputFile() {
		return outputFile;
	}
	
	// Initialise the starting folder to use when navigating files (Set to the home directory of the current user)
	private static File startingFolder = new File(System.getProperty("user.home"));
	
	// Prints the main menu
	protected static void printMenu() {
		System.out.print(ConsoleColour.WHITE);
		System.out.println("*************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics     *");
		System.out.println("*                                                           *");
		System.out.println("*       Comparing Text Documents with Virtual Threads       *");
		System.out.println("*                                                           *");
		System.out.println("*                Andrei Titilincu - G00472938               *");
		System.out.println("*************************************************************" + ConsoleColour.RESET);
		
		// The short hand if statement is used to only print the input and output file names if these files have been selected
		System.out.println("1. Specify query file." + (queryFile == null ? "" : ConsoleColour.GREEN + " (Currently " + queryFile.getName() + ")" + ConsoleColour.RESET));
		System.out.println("2. Specify subject file." + (subjectFile == null ? "" : ConsoleColour.GREEN + " (Currently " + subjectFile.getName() + ")" + ConsoleColour.RESET));
		System.out.println("3. Specify output file." + (outputFile == null ? "" : ConsoleColour.GREEN + " (Currently " + outputFile.getName() + ")" + ConsoleColour.RESET));
		System.out.println("4. Execute, Analyse and Report");
		System.out.println("5. Quit.\n");
		System.out.print(ConsoleColour.CYAN + "Select option: " + ConsoleColour.RESET); 
		
		// Initialise the scanner which will take in user input
		Scanner scanner = new Scanner(System.in);
		
		// Initialise the option variable to -1 (None selected)
		int option = -1;
		
		// Main menu loop
		while (true) {
			
			// Loop to ensure user has selected a valid option before proceeding
			while (true) {
				
				// If the input from the user is not an integer or is out of bounds of the range of possible options, 
				// the catch statement block will be executed
				try {
					option = Integer.parseInt(scanner.nextLine());
					if (option < 1 || option > 5) throw new IllegalArgumentException("Out of range");
					break;
				} catch (Exception e) {
					// Prompt the user to enter a suitable option
					System.out.print(ConsoleColour.RED + "\nPlease enter an integer between 1 and 5: " + ConsoleColour.RESET);
				}
			}
			
			// The scanner that was initialised is passed into the next stage of the program,
			// to prevent the unnecessary creation of another scannner object
			if (option == 1) {
				
			} else if (option == 2) {
				
			} else if (option == 3) {
			;
			} else if (option == 4) {
				
			} else if (option == 5) {
				
				// Quit the program
				scanner.close();
				System.exit(0);
			}
		}
	}
}
