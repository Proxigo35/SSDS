package ie.atu.sw;

import java.io.File;

public final class FileStore {

    private static File queryFile;
    private static File subjectFile;
    private static File outputFile;

    public static boolean set(FileType type, File file) {
        if (file == null) return false;

        switch (type) {
            case QUERY -> queryFile = file;
            case SUBJECT -> subjectFile = file;
            case OUTPUT -> outputFile = file;
        }
        return true;
    }

    public static File get(FileType type) {
        return switch (type) {
            case QUERY -> queryFile;
            case SUBJECT -> subjectFile;
            case OUTPUT -> outputFile;
        };
    }
}
