package ie.atu.sw;

import java.util.Scanner;
import java.io.IOException;

public class Menu {
	
	static Scanner scanner = new Scanner(System.in);

	public static void start() {
		printMenu();
		getInput();
	}

	protected static void printMenu() {
		System.out.print("" + ConsoleCode.CURSOR_HOME + ConsoleCode.CLEAR_SCREEN + ConsoleColour.YELLOW);
		System.out.println("*************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics     *");
		System.out.println("*                                                           *");
		System.out.println("*       Comparing Text Documents with Virtual Threads       *");
		System.out.println("*                                                           *");
		System.out.println("*                Andrei Titilincu - G00472938               *");
		System.out.println("*************************************************************" + ConsoleColour.RESET);
		System.out.println("1. Specify query file." + (FileStore.get(FileType.QUERY) == null ? "" : ConsoleColour.GREEN + " (Currently " + FileStore.get(FileType.QUERY).getName() + ")") + ConsoleColour.RESET);
		System.out.println("2. Specify subject file." + (FileStore.get(FileType.SUBJECT) == null ? "" : ConsoleColour.GREEN + " (Currently " + FileStore.get(FileType.SUBJECT).getName() + ")") + ConsoleColour.RESET);
		System.out.println("3. Change n-gram size." + ConsoleColour.GREEN + " (Currently " + DiceSimilarity.getNGramSize() + ")" + ConsoleColour.RESET); 
		System.out.println("4. Execute, Analyze and Report.");
		System.out.println("5. Quit.\n");
		System.out.print(ConsoleColour.CYAN + "Select option: " + ConsoleColour.RESET); 
	}

	protected static void getInput() {
		int option = -1;
		
		while (true) {
			while (true) {
				try {
					option = Integer.parseInt(scanner.nextLine());
					if (option >= 1 && option <= 5) break;
				} catch (NumberFormatException e) {}
				System.out.println(ConsoleColour.RED + "Please enter a valid integer between 1 and 5." + ConsoleColour.RESET);
			}
			
			if (option == 1) {
				if (FileStore.set(FileType.QUERY, FileChooser.chooseFile())) printMenu();
			} else if (option == 2) {
				if (FileStore.set(FileType.SUBJECT, FileChooser.chooseFile())) printMenu();
			} else if (option == 3) {
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
				if (FileStore.get(FileType.QUERY) != null && FileStore.get(FileType.SUBJECT) != null) {
					try {
						double similarity = DiceSimilarity.calculate(FileStore.get(FileType.QUERY), FileStore.get(FileType.SUBJECT));
						System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
						int size = 100;							//The size of the meter. 100 equates to 100%
						for (int i = 0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
							ProgressBar.print(i + 1, size); 	//After each (some) steps, update the progress meter
							Thread.sleep(10);					//Slows things down so the animation is visible 
						}
						System.out.printf(ConsoleColour.PURPLE + "Sorensen-Dice similarity : %.2f%%%n", similarity * 100);
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
