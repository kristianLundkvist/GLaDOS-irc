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

package se.bthstudent.sis.afk.GLaDOS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import net.sourceforge.jwbf.core.actions.util.ActionException;
import net.sourceforge.jwbf.core.actions.util.ProcessException;

/**
 * Wikipedia Search module of GLaDOS. Searches Wikipedia with the help of the WernickeModule.
 * @author Prosten
 * 
 */

public class SearchModule implements Serializable {

	/**
	 * Field serialVersionUID. (value is -3339908201447963373)
	 */
	private static final long serialVersionUID = -3339908201447963373L;

	
	public SearchModule() throws MalformedURLException, ActionException{
		
	}
		
	public String wikiSearch(String searchString) throws ActionException, ProcessException, IOException {
		//search code
		//mSimpleArticle = new SimpleArticle(mMediaWikiBot.readContent(searchString));
		String str = "";

		int fileLines = 0;

		 URL wikiURL = null;
		 wikiURL = new URL("http://en.wikipedia.org:80/w/index.php?title=Special%3ASearch&ns0=1&search=" + searchString + "&=MediaWiki+search&fulltext=Advanced+search");
		 BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
		 
		 BufferedReader in = new BufferedReader(new InputStreamReader(wikiURL.openStream()));
	 
	 
		 while((str = in.readLine()) != null)
		 {
			 str += "<br>";
			 out.write(str);
			 fileLines++;
		 }
		 out.close();
		 
		 
			 
		return "hai";
	}
	
}
