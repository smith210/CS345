import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException, invalidPlayerException {
		Deadwood game = new Deadwood();
		game.run(Integer.parseInt(args[0]));
	}
}
