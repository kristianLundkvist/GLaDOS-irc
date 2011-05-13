package se.bthstudent.sis.afk.GLaDOS;

/**
 * Message parsing part of GLaDOS. Splits commands from users and other useful functions.
 * @author Sabbath
 *
 */
public class MessageParse {
	
	private String parse;
	
	/**
	 * Default constructor
	 */
	public MessageParse(){
		this.parse = "";
	}
	
	/**
	 * Checks if the message is a command to GLaDOS, i.e. the first character is '!'.
	 * @param message Message to check
	 * @return Returns true if the message is a command, false otherwise.
	 */
	public boolean isCommand(String message){
		if(message.substring(0, 1).equalsIgnoreCase("!"))
			return true;
		else
			return false;
	}
	
	/**
	 * Splits a message into manageable chunks to be processed by GLaDOS.
	 * @param parse The message to parse.
	 * @return Returns an empty String array if the message isn't a command. If the
	 * message is a command the message will be split up into pieces, the first one
	 * (at index 0) will be the specific command, the rest will be arguments to the
	 * command. 
	 */
	public String[] parseString(String parse){
		this.parse = parse;
		String[] toReturn = new String[0];
		
		if(this.isCommand(this.parse)){
			this.parse = this.parse.substring(1, this.parse.length());
			toReturn = this.parse.split("\\s");
		}
		else{
			toReturn = new String[0];
		}
		
		return toReturn;
	}
}
