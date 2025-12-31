package ie.atu.sw;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.Duration;

/**
* Computes the Sørensen–Dice similarity coefficient between two text files.
* <p>
* Each file is parsed into word-based n-grams (also called shingles).
* Parsing is performed concurrently using Java virtual threads.
* </p>
*
* <p>
* The Sørensen–Dice coefficient is defined as:
* </p>
*
* <pre>
* Dice(A, B) = 2 * |A ∩ B| / (|A| + |B|)
* </pre>
*
* where A and B are sets of n-grams extracted from each document.
*/
public class DiceSimilarity {

    /**
    * Size of the n-grams (default = 2, i.e. bigrams).
    * Can be changed at runtime via the menu.
    */
    private static int nGramSize = 2;

    /**
    * Sets the size of the n-grams used during parsing.
    *
    * @param size the new n-gram size (must be >= 1)
    */
    public static void setNGramSize(int size) {
        nGramSize = size;
    }

    /**
    * Returns the currently configured n-gram size.
    *
    * @return n-gram size
    */
    public static int getNGramSize() {
        return nGramSize;
    }

    /**
    * Parses a text file into a set of unique n-gram shingles.
    *
    * <p>
	* Each line of the file is processed independently and submitted
    * as a task to a virtual-thread-backed executor.
    * </p>
	*
    * <p>
	* A thread-safe {@link Set} is used because multiple virtual threads
    * may attempt to add shingles concurrently.
    * </p>
    *
	* @param file     the text file to parse
	* @param executor executor service backed by virtual threads
	* @param linesProcessed atomic counter used for progress tracking
    * @return a set of unique shingles extracted from the file
    * @throws IOException if the file cannot be read
	*/
    private static Set<String> parseFileToShingles(File file, ExecutorService executor, AtomicInteger linesProcessed) throws IOException {

        // Thread-safe data structure - ConcurrentHashMap
        Set<String> shingles = ConcurrentHashMap.newKeySet();

        /*
        * Files.lines() returns a lazily-evaluated stream of lines.
        * Each line is submitted as a separate task, allowing
        * parsing to scale efficiently with large files.
        */

		// try with resources, for automatic closing.
		// Files.lines reads one line at a time.
		try (var lines = Files.lines(file.toPath())) {
			lines.forEach(line ->
				executor.submit(() -> { // Each line submitted as a separate task.

					List<String> tokens = Arrays.stream(line.split("\\W+")) // Splits the line into words.
												.map(String::toLowerCase) // Makes all text lower case
												.filter(token -> !token.isEmpty()) // Removes empty strings incase they entered the stream.
												.toList();

					// Loops through tokens, and adds to shingles elements each containing nGramSize words.
					// Words can be part of mutliple shingle elements for better comparison.
					for (int i = 0; i <= tokens.size() - nGramSize; i++) {
						shingles.add(String.join(" ", tokens.subList(i, i + nGramSize)));
					}

					linesProcessed.incrementAndGet();
				})
			);
		}
        return shingles;
    }

    /**
    * Calculates the Sørensen–Dice similarity between two files.
    *
    * <p>
    * Both files are parsed concurrently using virtual threads.
    * The executor is shut down only after all parsing tasks complete.
    * </p>
    *
    * @param queryFile first file
    * @param subjectFile second file
    * @return similarity score in the range [0.0, 1.0]
    * @throws IOException          if a file cannot be read
    * @throws InterruptedException if execution is interrupted
    */
    public static double calculate(File queryFile, File subjectFile) throws IOException, InterruptedException {

        // Each parsing task gets its own virtual thread.
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
		AtomicInteger linesProcessed = new AtomicInteger(0);

		// Count total lines for progress bar
		int totalLines = (int) Files.lines(queryFile.toPath()).count() + (int) Files.lines(subjectFile.toPath()).count();

		// Start a thread to update progress bar
		Thread progressThread = new Thread(() -> {
			while (linesProcessed.get() < totalLines) {
				ProgressBar.print(linesProcessed.get(), totalLines);
				try {
					Thread.sleep(Duration.ofMillis(10)); // Put the thread to sleep to give other processes time to run
				} catch (InterruptedException e) {
					break;
				}
			}
			ProgressBar.print(totalLines, totalLines); // ensure full bar
		});
		progressThread.start();

        // Parse both files using the same executor
        Set<String> shinglesA = parseFileToShingles(queryFile, executor, linesProcessed);
        Set<String> shinglesB = parseFileToShingles(subjectFile, executor, linesProcessed);

        // Shutdown prevents new tasks from being submitted.
        executor.shutdown();
        // awaitTermination blocks until all parsing tasks complete, using the max value of Long and setting time to nanoseconds effectively gives the executor a very very long time to execute
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		// Wait for progress thread to finish
		progressThread.join();

        if (shinglesA.isEmpty() && shinglesB.isEmpty()) return 1.0; // if both files are empty, they are exaclty the same even though they have no content
        if (shinglesA.isEmpty() || shinglesB.isEmpty()) return 0.0; // if only one is empty, there is no similarity

        // Compute intersection without affecting original sets.
        Set<String> intersection = new HashSet<>(shinglesA);
        intersection.retainAll(shinglesB);

        // Sørensen–Dice formula
        return (2.0 * intersection.size()) / (shinglesA.size() + shinglesB.size());
    }
}
