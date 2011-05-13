import org.jibble.pircbot.*;

public class GLaDOSMain {

	public static void main(String[] args) throws Exception
	{
		// Start GLaDOS
		GLaDOS bot = new GLaDOS();
		
		// Enable debugging output
		bot.setVerbose(true);
		
		// Connect her to BSnet IRC-server
		bot.connect("irc.bsnet.se");
		
		// Make her join the testchannel
		bot.joinChannel("#GLaDOStest");
	}
}
