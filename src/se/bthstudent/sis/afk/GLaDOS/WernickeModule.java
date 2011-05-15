/*
    Copyright (C) 2011  Kristian 'Bobby' Lundkvist, Niclas 'Prosten' Bj�rner

	This file is a part of GLaDOS

    This GLaDOS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.bthstudent.sis.afk.GLaDOS;

import java.io.Serializable;

/**
 * Message parsing part of GLaDOS. Splits commands from users and other useful
 * functions. An explanation of sorts for the name can be found at
 * http://en.wikipedia.org/wiki/Wernicke%27s_area
 * 
 * @author Bobby
 * 
 * @version $Revision: 1.0 $
 */
public class WernickeModule implements Serializable {

	/**
	 * Field serialVersionUID.
	 * (value is -5289384551294934213)
	 */
	private static final long serialVersionUID = -5289384551294934213L;
	
	/**
	 * String used to save the message to Parse
	 */
	private String parse;

	/**
	 * Default constructor
	 */
	public WernickeModule() {
		this.parse = "";
	}

	/**
	 * Checks if the message is a command to GLaDOS, i.e. the first character is
	 * '!'.
	 * 
	 * @param message
	 *            Message to check	
	 * @return Returns true if the message is a command, false otherwise. */
	public boolean isCommand(String message) {
		return message.substring(0, 1).equalsIgnoreCase("!");
	}

	/**
	 * Splits a message into manageable chunks to be processed by GLaDOS.
	 * 
	 * @param parse
	 *            The message to parse.
	 *            
	 * @return Returns an empty String array if the message isn't a command. If
	 *         the message is a command the message will be split up into
	 *         pieces, the first one (at index 0) will be the specific command,
	 *         the rest will be arguments to the command. 
	 *         */
	public String[] parseString(String parse) {
		this.parse = parse;
		String[] toReturn = new String[0];

		if (this.isCommand(this.parse)) {
			this.parse = this.parse.substring(1, this.parse.length());
			toReturn = this.parse.split("\\s");
		} else {
			toReturn = new String[0];
		}

		return toReturn;
	}
}
