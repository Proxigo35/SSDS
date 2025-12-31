package ie.atu.sw;

/**
* ANSI colour escape codes for changing text colour in console.
*/
public enum ConsoleColour {
    RESET("0"),
    RED("0;31"),
    GREEN("0;32"),
    YELLOW("0;33"),
    PURPLE("0;35"),
    CYAN("0;36");

    private static final String PREFIX = "\033[";
    private static final String SUFFIX = "m";

    private final String code;

    ConsoleColour(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return PREFIX + code + SUFFIX;
    }
}
