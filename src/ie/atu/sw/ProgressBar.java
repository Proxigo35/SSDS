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
        if (index == total) System.out.println("\n");
    }
}
