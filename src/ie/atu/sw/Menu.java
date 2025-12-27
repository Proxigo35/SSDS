package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	
	static Scanner scanner = new Scanner(System.in);

	protected static void printMenu() {
		System.out.print("" + ConsoleCode.CURSOR_HOME + ConsoleCode.CLEAR_SCREEN + ConsoleColour.YELLOW);
		System.out.println("*************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics     *");
		System.out.println("*                                                           *");
		System.out.println("*       Comparing Text Documents with Virtual Threads       *");
		System.out.println("*                                                           *");
		System.out.println("*                Andrei Titilincu - G00472938               *");
		System.out.println("*************************************************************" + ConsoleColour.RESET);
		System.out.println("1. Specify query file." + (Files.get("query") == null ? "" : ConsoleColour.GREEN + " (Currently " + Files.get("query").getName() + ")" + ConsoleColour.RESET));
		System.out.println("2. Specify subject file." + (Files.get("subject") == null ? "" : ConsoleColour.GREEN + " (Currently " + Files.get("subject").getName() + ")" + ConsoleColour.RESET));
		System.out.println("3. Specify output file." + (Files.get("output") == null ? "" : ConsoleColour.GREEN + " (Currently " + Files.get("output").getName() + ")" + ConsoleColour.RESET));
		System.out.println("4. Execute, Analyse and Report.");
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
				if (Files.set("query", FileChooser.chooseFile())) printMenu();
			} else if (option == 2) {
				if (Files.set("subject", FileChooser.chooseFile())) printMenu();
			} else if (option == 3) {
				if (Files.set("output", FileChooser.chooseSaveFile())) printMenu();
			} else if (option == 4) {
			} else if (option == 5) {
				scanner.close();
				System.exit(0);
			}
		}
	}
}
