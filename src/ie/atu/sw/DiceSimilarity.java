package ie.atu.sw;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class DiceSimilarity {

	private static int nGramSize = 2;

	protected static void setNGramSize(int size) {
		nGramSize = size;
	}

	protected static int getNGramSize() {
		return nGramSize;
	}

    private static Set<String> parseFileToShingles(File file, ExecutorService executor) throws IOException {
        Set<String> shingles = ConcurrentHashMap.newKeySet();

		try (var lines = Files.lines(file.toPath())) {
    		lines.forEach(line -> executor.submit(() -> {
				List<String> tokens = Arrays.stream(line.split("\\W+"))
											.map(String::toLowerCase)
											.filter(t -> !t.isEmpty())
											.toList();
				for (int i = 0; i <= tokens.size() - nGramSize; i++) shingles.add(String.join(" ", tokens.subList(i, i + nGramSize)));
			}));
		}

        return shingles;
    }

    public static double calculate(File fileA, File fileB) throws InterruptedException, IOException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        Set<String> shinglesA = parseFileToShingles(fileA, executor);
        Set<String> shinglesB = parseFileToShingles(fileB, executor);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        Set<String> intersection = new HashSet<>(shinglesA);
        intersection.retainAll(shinglesB);

        return (2.0 * intersection.size()) / (shinglesA.size() + shinglesB.size());
    }

}
