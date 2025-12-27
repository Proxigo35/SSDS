package ie.atu.sw;

public enum ConsoleColour {
    RESET						("Reset",						"0"),

    BLACK						("Black [Regular]",				"0;30"),
    RED							("Red [Regular]", 				"0;31"),
    GREEN						("Green [Regular]",				"0;32"),
    YELLOW						("Yellow [Regular]", 			"0;33"),
    BLUE						("Blue [Regular]", 				"0;34"),
    PURPLE						("Purple [Regular]", 			"0;35"),
    CYAN						("Cyan [Regular]", 				"0;36"),
    WHITE						("White [Regular]", 			"0;37");
	
	//Control Sequence Introducer. ASCII Octal = \033, ASCII Hex = \0x1B, Shell = \e
	private static final String CTRL_SEQ_INTRO = "\033[";  
	private static final String CTRL_SEQ_END = "m"; //Terminates control  
	private final String description;
	private final String colour;
	
	ConsoleColour(String description, String colour) {
		this.description = description;
		this.colour = colour;
	}
	
	public String description() { 
		return this.description; 
	}

	public String colour() { 
		return toString();
	}
	
	@Override
    public String toString() {
        return CTRL_SEQ_INTRO + this.colour + CTRL_SEQ_END; 
    }
}
