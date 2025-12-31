package ie.atu.sw;

/**
* Application entry point.
*
* <p>
* Initializes and launches the console menu interface.
* </p>
*/
public class Runner {

	/**
	* Starts the application.
	*
	* @param args command-line arguments (not used)
	*/
    public static void main(String[] args) {
        Menu.printMenu();
		Menu.getInput();
    }

}
