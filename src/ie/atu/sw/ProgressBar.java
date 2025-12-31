package ie.atu.sw;

/**
* Console progress bar.
*/
public class ProgressBar {

	/**
	* Prints a console-based progress bar.
	*
	* @param index the current progress value
	* @param total the total value representing 100% completion
	*/
    public static void print(int index, int total) {
        int size = 50; // Must be less than console width.
        int percent = (100 * index) / total;
        int filled = size * percent / 100;

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < size; i++) {
            bar.append(i < filled ? '█' : '░');
        }

        System.out.print(ConsoleColour.YELLOW + "\r" + bar + " " + percent + "%" + ConsoleColour.RESET);
        if (index == total) System.out.println();
    }
}
