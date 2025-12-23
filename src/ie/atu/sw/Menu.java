package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	
	// Initialise the starting folder to use when navigating files (Set to the home directory of the current user)
	// private static File startingFolder = new File(System.getProperty("user.home"));
	
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
		System.out.println("1. Specify query file." + (Files.get("query") == null ? "" : ConsoleColour.GREEN + " (Currently " + Files.get("query").getName() + ")" + ConsoleColour.RESET));
		System.out.println("2. Specify subject file." + (Files.get("subject") == null ? "" : ConsoleColour.GREEN + " (Currently " + Files.get("subject").getName() + ")" + ConsoleColour.RESET));
		System.out.println("3. Specify output file." + (Files.get("output") == null ? "" : ConsoleColour.GREEN + " (Currently " + Files.get("output").getName() + ")" + ConsoleColour.RESET));
		System.out.println("4. Execute, Analyse and Report.");
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
				
				// User must enter integer between 1 and 5 to proceed with program. 
				try {
					option = Integer.parseInt(scanner.nextLine());
					if (option >= 1 && option <= 5) break;
				} catch (NumberFormatException e) {}
				System.out.println(ConsoleColour.RED + "Please enter a valid integer between 1 and 5." + ConsoleColour.RESET);
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
