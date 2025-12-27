package ie.atu.sw;

// Required for the Arrays.equals() method
import java.util.Arrays;

public class ArrayMethods {
	
	// Returns a String array with the String provided appended to the end of the array
	protected static String[] appendToArray(String[] array, String value) {
		
		// Declare a temporary array with an increased capacity of 1 compared to the input array
		String[] newArray = new String[array.length + 1];
		
		// Copy all elements from the input array to the temporary array
		for (int i = 0; i < array.length; i++) newArray[i] = array[i];
		
		// Set the last value of the tempory array to the value to be appended
		newArray[array.length] = value;
		return newArray;
	}
	
	// Returns a 2D String array with the String array provided appended to the end of the 2D array
	protected static String[][] appendToArray(String[][] array, String[] value) {
		
		// Declare a temporary array with an increased row capacity of 1 compared to the input array
		String[][] newArray = new String[array.length + 1][2];
		
		// Copy all elements from the input array to the temporary array
		for (int i = 0; i < array.length; i++) {
			newArray[i][0] = array[i][0];
			newArray[i][1] = array[i][1];
		}
		
		// Set the last value of the tempory array to the value array to be appended
		newArray[array.length] = value;
		return newArray;
	}
	
	// Returns a 2D array with the value provided removed from the input array
	protected static String[][] remove(String[][] array, String[] value) {
		
		// Declare an array with a decreased capacity of 1 compared to the input array
		String[][] newArray = new String[array.length - 1][2];
		
		// Initialise a variable to keep track of the index when looping, as deleting an element will affect this
		int back = 0;
		
		// Loop throught the input array
		for (int i = 0; i < array.length; i++) {
			
			// If the value at the current index of the array is not equal to the value to be removed, copy it to the temporary array
			// The variable back ensures that new elements are added in succession, and therefore there are no empty gaps in the temporary array
			if (!Arrays.equals(array[i], value)) newArray[i - back] = array[i];
			
			// Otherwise, increment the value of 'back' (the value will not be copied to the temporary array)
			else back++;
		}
		return newArray;
	}

}
