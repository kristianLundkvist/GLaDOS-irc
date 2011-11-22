/*
    Copyright (C) 2011  Kristian 'Bobby' Lundkvist, Niclas 'Prosten' Björner

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

/**
 * Memorymodule for different Memory-related features
 * Such as an IOU feature
 * 
 * @author Prosten
 * 
 */

package se.bthstudent.sis.afk.GLaDOS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jibble.jmegahal.JMegaHal;

public class MemoryModule implements Serializable {

	/**
	 * Field serialVersionUID.
	 */
	private static final long serialVersionUID = 8797401236088621457L;

	/* The arrays that holds the user information */
	String owe[][];

	/* The arrays that keeps the amount */
	int amount[][];

	public MemoryModule() {
		amount = new int[1][1];
		owe = new String[1][1];
		amount[0][0] = 0;
		owe[0][0] = "";
	}

	public boolean add(String name1, String name2, int Amount) {
		boolean done = false;
		boolean found1 = false;
		boolean found2 = false;

		for (int i = 0; i < owe.length; i++) {
			if (owe[i][0].contentEquals(name1)) {
				found1 = true;
				for (int j = 0; j < owe[i].length; j++) {
					if (owe[i][j].contentEquals(name2)) {
						found2 = true;
						amount[i][j] += Amount;
						done = true;
					}
				}
				if (!found2) {
					insertNewOwe(owe[i], amount[i], name2, Amount);
					done = true;
				}
			}
		}
		if (!found1) {
			insertAllNew(owe, amount, name1, name2, Amount);
			done = true;
		}
		return done;
	}

	private void insertAllNew(String[][] array, int[][] array2, String Owner,
			String Owniee, int Amount) {
		String[][] temp1 = array;
		int[][] temp2 = array2;

		owe = new String[temp1.length + 1][];
		System.arraycopy(temp1, 0, owe, 0, temp1.length);

		for (int i = 0; i < temp1.length; i++) {
			owe[i] = new String[temp1[i].length];
			System.arraycopy(temp1[i], 0, owe[i], 0, temp1[i].length);
		}

		amount = new int[temp2.length + 1][];
		System.arraycopy(temp2, 0, amount, 0, temp2.length);

		for (int i = 0; i < temp2.length; i++) {
			amount[i] = new int[temp2[i].length];
			System.arraycopy(temp2[i], 0, amount[i], 0, temp2[i].length);
		}

		owe[owe.length - 1] = new String[2];
		owe[owe.length - 1][0] = Owner;
		owe[owe.length - 1][1] = Owniee;
		amount[amount.length - 1] = new int[2];
		amount[amount.length - 1][0] = 0;
		amount[amount.length - 1][1] = Amount;
	}

	private void insertNewOwe(String[] array, int[] array2, String name,
			int content) {
		String[] oweTemp = array;
		int[] amountTemp = array2;

		array = new String[oweTemp.length + 1];
		array2 = new int[amountTemp.length + 1];

		System.arraycopy(oweTemp, 0, array, 0, oweTemp.length);
		System.arraycopy(amountTemp, 0, array, 0, amountTemp.length);

		array[array.length - 1] = name;
		array2[array2.length - 1] = content;
	}

	public String owesMe(String nick, String own) {
		String message = "No one owes " + nick + " any money.";
		boolean found1 = false;
		boolean found2 = false;

		for (int i = 0; i < owe.length; i++) {
			if (owe[i][0].contentEquals(nick)) {
				found1 = true;
				for (int j = 0; j < owe[i].length; j++) {
					if (owe[i][j].contentEquals(own)) {
						found2 = true;
						message = own + " Owes " + nick + " "
								+ Integer.toString(amount[i][j]) + " SEK.";
					}
				}
				if (found1 && !found2) {
					message = own + " does now owe " + nick + " any money.";
				}
			}
		}
		return message;
	}

	public MemoryModule load() {
		FileInputStream fis;
        ObjectInputStream in;
        MemoryModule obj = null;
        //
        try{
            fis = new FileInputStream("IOWEYOU.backup");
            in = new ObjectInputStream(fis);
            obj = (MemoryModule)in.readObject();
            in.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        if(obj == null){
            obj = new MemoryModule();
        }
        else {
        	System.out.println("Loaded MemoryModule from Back-up");
        }
        return obj;
	}

	public void save(MemoryModule obj) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream("IOWEYOU.backup");
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
