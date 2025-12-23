package ie.atu.sw;

public class ProgressBar {

	public static void print(int index, int total) {
		if (index > total) return;	//Out of range
        int size = 50; 				//Must be less than console width
	    char done = '█';
	    char todo = '░';
	    
	    //Compute basic metrics for the meter
        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
        	sb.append((i < completeLen) ? done : todo);
        }
        
        System.out.print("\r" + sb + complete + "%");
        
        if (done == total) System.out.println("\n");
    }
		// System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
		// int size = 100;							//The size of the meter. 100 equates to 100%
		// for (int i = 0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
		// 	ProgressBar.print(i + 1, size); 	//After each (some) steps, update the progress meter
		// 	Thread.sleep(10);					//Slows things down so the animation is visible 
		// }
}
