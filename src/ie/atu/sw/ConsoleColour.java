package ie.atu.sw;

public enum ConsoleColour {
    RESET("0"),
    BLACK("0;30"),
    RED	("0;31"),
    GREEN("0;32"),
    YELLOW("0;33"),
    BLUE("0;34"),
    PURPLE("0;35"),
    CYAN("0;36"),
    WHITE("0;37");
	
	//Control Sequence Introducer. ASCII Octal = \033, ASCII Hex = \0x1B, Shell = \e
	private static final String CTRL_SEQ_INTRO = "\033[";  
	private static final String CTRL_SEQ_END = "m"; //Terminates control  
	private final String colour;
	
	ConsoleColour(String colour) {
		this.colour = colour;
	}

	public String colour() { 
		return toString();
	}
	
	@Override
    public String toString() {
        return CTRL_SEQ_INTRO + this.colour + CTRL_SEQ_END; 
    }
}
