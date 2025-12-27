package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {

	public static void main(String[] args) throws Exception {
		
		Menu.printMenu();
		Menu.getInput();
		
		System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
		int size = 100;							//The size of the meter. 100 equates to 100%
		for (int i = 0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
			ProgressBar.print(i + 1, size); 	//After each (some) steps, update the progress meter
			Thread.sleep(10);					//Slows things down so the animation is visible 
		}
	}

}
