package ie.atu.sw;

public enum ConsoleCode {
	CLEAR_TO_END ("\033[0J"),
	CLEAR_SCREEN ("\033[2J"),
	CURSOR_HOME ("\033[H"),
	SET_CURSOR ("\0337"),
	RESTORE_CURSOR ("\0338");

	private final String code;
	ConsoleCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
