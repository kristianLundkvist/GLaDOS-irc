package se.bthstudent.sis.afk.GLaDOS;

import java.io.IOException;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

/**
 * Main class for GLaDOS
 * @author Sabbath, Prosten
 *
 */
public class GLaDOSMain {

	/**
	 * Starts GLaDOS, connects to server and join channels.
	 * @param args Runtime arguments
	 */
	public static void main(String[] args)
	{
		GLaDOS bot = new GLaDOS();
		
		bot.setVerbose(true);
		
		try {
			bot.connect("irc.bsnet.se");
		} 
		catch (NickAlreadyInUseException e) {
			System.out.println("Error: Nick was already in use");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (IrcException e) {
			e.printStackTrace();
		}
		
		bot.joinChannel("#GLaDOStest");
	}
}
