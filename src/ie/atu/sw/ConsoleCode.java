package ie.atu.sw;

/**
* ANSI console control escape codes for clearing and cursor positioning.
*/
public enum ConsoleCode {
    CLEAR_TO_END("\033[0J"),
    CLEAR_SCREEN("\033[2J"),
	CURSOR_HOME("\033[H");

    private final String code;

    ConsoleCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
