package se.bthstudent.sis.afk;

import java.io.Serializable;

public class TestSubject implements Serializable{
	
	private static final long serialVersionUID = -1285284334132918163L;
	private String nick;
	private String[] alias;
	public enum Mode{
		OP, VOICE, NONE;
	}
	
	private Mode mode;
	
	public TestSubject(){
		this.setNick("");
		this.alias = new String[0];
		this.setMode(Mode.NONE);
	}
	
	public TestSubject(String nick, String[] alias, Mode mode){
		this.setNick(nick);
		this.alias = alias;
		this.setMode(mode);
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}
	
	/**
	 * Adds an alias to the test subjects nick
	 * @param alias Alias to be added
	 */
	public void addAlias(String alias){
		String[] temp = new String[this.alias.length+1];
		
		for(int i = 0; i < this.alias.length; i++){
			temp[i] = this.alias[i];
		}
		
		temp[this.alias.length+1] = alias;
		
		this.alias = temp;
	}
	
	/**
	 * Checks if the test subject have a specific alias
	 * @param toFind The alias to search for.
	 * @return Returns true if the alias is found, false otherwise
	 */
	public boolean checkForAlias(String toFind){
		boolean found = false;
		
		for(String alias : this.alias){
			if(alias.equals(toFind))
				found = true;
		}
		
		return found;
	}
}