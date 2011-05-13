package se.bthstudent.sis.afk.GLaDOS;

public class MessageParse {
	
	private String parse;
	
	public MessageParse(){
		this.parse = "";
	}
	
	public boolean isCommand(String message){
		if(message.substring(0, 1).equalsIgnoreCase("!"))
			return true;
		else
			return false;
	}
	
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
