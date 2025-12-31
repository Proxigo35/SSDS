package ie.atu.sw;

import java.util.Scanner;
import java.io.IOException;

/**
* Console-based menu system for interacting with the application.
*
* <p>
* This class is responsible for printing the user interface, handling user input, validating menu selections, and coordinating file selection and similarity execution.
* </p>
*/
public class Menu {
	
	static Scanner scanner = new Scanner(System.in);

	/**
	* Prints the main application menu to the console.
	*
	* <p>
	* Displays the current application state including selected query and subject files and the active n-gram size.
	* </p>
	*/
	protected static void printMenu() {
		System.out.print("" + ConsoleCode.CURSOR_HOME + ConsoleCode.CLEAR_SCREEN + ConsoleColour.YELLOW);
		System.out.println("*************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics     *");
		System.out.println("*                                                           *");
		System.out.println("*       Comparing Text Documents with Virtual Threads       *");
		System.out.println("*                                                           *");
		System.out.println("*                Andrei Titilincu - G00472938               *");
		System.out.println("*************************************************************" + ConsoleColour.RESET);
		
		// Shorthand if statement used to print names of query and subject files, and n-gram size if already chosen.
		System.out.println("1. Specify query file." + (FileStore.get(FileType.QUERY) == null ? "" : ConsoleColour.GREEN + " (Currently " + FileStore.get(FileType.QUERY).getName() + ")") + ConsoleColour.RESET);
		System.out.println("2. Specify subject file." + (FileStore.get(FileType.SUBJECT) == null ? "" : ConsoleColour.GREEN + " (Currently " + FileStore.get(FileType.SUBJECT).getName() + ")") + ConsoleColour.RESET);
		System.out.println("3. Change n-gram size." + ConsoleColour.GREEN + " (Currently " + DiceSimilarity.getNGramSize() + ")" + ConsoleColour.RESET); 
		System.out.println("4. Execute, Analyze and Report.");
		System.out.println("5. Quit.\n");
		System.out.print(ConsoleColour.CYAN + "Select option: " + ConsoleColour.RESET); 
	}

	/**
	* Handles user interaction and menu selection.
	*
	* <p>
	* Continuously prompts the user for input, validates menu selections, and executes the requested actions until the user chooses to exit the application.
	* </p>
	*/
	protected static void getInput() {
	
	// Set option to invalid value before user alters it. 
		int option = -1;
		
		// Menu loop
		while (true) {
			// Continue looping until user enters valid input.
			while (true) {
				try {
					option = Integer.parseInt(scanner.nextLine());
					if (option >= 1 && option <= 5) break; // User selected valid input.
				} catch (NumberFormatException e) {} // This exception is thrown if the program fails to parse the input to an integer.
				System.out.println(ConsoleColour.RED + "Please enter a valid integer between 1 and 5." + ConsoleColour.RESET);
			}
			
			if (option == 1) {
				if (FileStore.set(FileType.QUERY, FileChooser.chooseFile())) printMenu();
			} else if (option == 2) {
				if (FileStore.set(FileType.SUBJECT, FileChooser.chooseFile())) printMenu();
			} else if (option == 3) {
				// Continue looping until user enters valid input.
				while (true) {
					try {
						System.out.print(ConsoleColour.CYAN + "Please enter new n-gram size (1-10): " + ConsoleColour.RESET);
						int nGramSize = Integer.parseInt(scanner.nextLine());

						if (nGramSize >= 1 && nGramSize <= 10) {
							DiceSimilarity.setNGramSize(nGramSize);
							break;
						}
					} catch (NumberFormatException e) {}
					System.out.println(ConsoleColour.RED + "Please enter a valid integer between 1 and 10." + ConsoleColour.RESET);
				}

				printMenu();
			} else if (option == 4) {
				if (FileStore.readyForComparison()) {
					try {
						double similarity = DiceSimilarity.calculate(FileStore.get(FileType.QUERY), FileStore.get(FileType.SUBJECT));
						System.out.printf(ConsoleColour.PURPLE + "Sorensen-Dice similarity : %.2f%%%n" + ConsoleColour.RESET, similarity * 100);
						System.out.print(ConsoleColour.CYAN + "\nSelect option: " + ConsoleColour.RESET); 
					} catch (InterruptedException | IOException e) {
						System.out.println(ConsoleColour.RED + "Error calculating similarity: " + e.getMessage() + ConsoleColour.RESET);
						e.printStackTrace();
					}
				}
				else System.out.println(ConsoleColour.RED + "Choose query and subject file before proceeding." + ConsoleColour.RESET);
			} else if (option == 5) {
				scanner.close();
				System.exit(0);
			}
		}
	}
}
