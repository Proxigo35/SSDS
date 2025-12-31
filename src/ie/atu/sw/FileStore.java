package ie.atu.sw;

import java.io.File;

/**
* Central store for user-selected files.
* Avoids passing File references throughout the application.
*/
public final class FileStore {

    private static File queryFile;
    private static File subjectFile;
    private static File outputFile;

	// Setter method for all three files, where file is specified.
	// boolean is returned to decide if menu should be reprinted when called in Menu.java

	/**
	* Stores a file reference for later use by the application.
	*
	* @param type the logical role of the file (query, subject, or output)
	* @param file the file to store
	* @return {@code true} if the file was stored successfully, {@code false} otherwise
	*/
    public static boolean set(FileType type, File file) {
        if (file == null) return false;

        switch (type) {
            case QUERY -> queryFile = file;
            case SUBJECT -> subjectFile = file;
            case OUTPUT -> outputFile = file;
        }
        return true;
    }

	// Getter method for all three files, where file is specified.

	/**
	* Retrieves a stored file reference.
	*
	* @param type the logical role of the file
	* @return the associated {@link File}, or {@code null} if not set
	*/
    public static File get(FileType type) {
        return switch (type) {
            case QUERY -> queryFile;
            case SUBJECT -> subjectFile;
            case OUTPUT -> outputFile;
        };
    }

	// Used in Menu.java to prevent errors if trying to calculate similarity without query and subject files being chosen.

	/**
	* Indicates whether the application has sufficient input to perform a similarity comparison.
	*
	* @return {@code true} if both query and subject files are set
	*/
    public static boolean readyForComparison() {
        return queryFile != null && subjectFile != null;
    }
}
