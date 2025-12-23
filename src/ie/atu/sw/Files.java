package ie.atu.sw;

import java.io.File;

public class Files {

	private static File queryFile;
	private static File subjectFile;
	private static File outputFile;

	protected static void set(String filename, File file) {
		if (filename == "query") queryFile = file;
		else if (filename == "subject") subjectFile = file;
		else if (filename == "output") outputFile = file;
		else throw new IllegalArgumentException("Invalid filename entered.");
	}
	
	protected static File get(String filename) {
		if (filename == "query") return queryFile;
		else if (filename == "subject") return subjectFile;
		else if (filename == "output") return outputFile;
		else throw new IllegalArgumentException("Invalid filename entered.");
	}

}
