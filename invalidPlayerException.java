import java.util.*;

public class invalidPlayerException extends Exception {
	public invalidPlayerException(){
		super("Invalid number of players");
	}
}
