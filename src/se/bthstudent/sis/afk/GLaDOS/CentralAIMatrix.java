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
 * AI control part of GLaDOS. Saves new info to the "brain" and prints generated messages.
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
import org.jibble.jmegahal.*;

public class CentralAIMatrix implements Serializable {
	
	private static final long serialVersionUID = -3963603794608000452L;
	private JMegaHal hal;
	
	public CentralAIMatrix()
	{
		this.hal = load();
	}
	
	public String response()
	{
		return this.hal.getSentence();
	}
	
	public String specificResponse(String message)
	{
		return this.hal.getSentence(message);
	}
	
	public void addToIntellect(String message)
	{
		this.hal.add(message);
	}
	
	public void backUp()
	{
		save(this.hal);
	}
	
	public static JMegaHal load()
	{
        FileInputStream fis;
        ObjectInputStream in;
        JMegaHal obj = null;
        //
        try{
            fis = new FileInputStream("CentralAIMatrix.backup");
            in = new ObjectInputStream(fis);
            obj = (JMegaHal)in.readObject();
            in.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        if(obj == null){
            obj = new JMegaHal();
        }
        else {
        	System.out.println("Loaded GLaDOS-AI from Back-up");
        }
        return obj;
	}
	
	public static void save(JMegaHal obj)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream("CentralAIMatrix.backup");
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

}
