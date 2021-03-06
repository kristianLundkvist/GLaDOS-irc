/*
    Copyright (C) 2011  Kristian 'Bobby' Lundkvist, Niclas 'Prosten' BjÃ¶rner

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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Wikipedia Search module of GLaDOS. Searches Wikipedia with the help of the
 * WernickeModule.
 * 
 * @author Prosten
 * 
 */

public class SearchModule implements Serializable {

	/**
	 * Field serialVersionUID. (value is -3339908201447963373)
	 */
	private static final long serialVersionUID = -3339908201447963373L;

	public SearchModule() throws MalformedURLException {

	}

	public String wikiSearch(String searchString) throws IOException {
		// search code

		// set all the strings that I will need in the extraction
		String str = "";
		String newString = "";
		String information = "";
		String URL = "";
		URL wikiURL = null;

		// get the URL to the Wiki-search and start all the writers and readers
		wikiURL = new URL(
				"http://en.wikipedia.org:80/w/index.php?title=Special%3ASearch&ns0=1&search="
						+ searchString
						+ "&=MediaWiki+search&fulltext=Advanced+search");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				wikiURL.openStream()));

		// Add <br> so that I have something to go on when I split it up later.
		while ((str = in.readLine()) != null) {
			str += "<br>";
			newString += str;
		}

		// Split the string up into an String array based on where you find the
		// <br> tag
		String[] x = Pattern.compile("<br>").split(newString);

		boolean done = false;
		boolean found = false;

		// Search for the most relevant information
		for (int i = 0; i < x.length; i++) {
			if (!done) {
				if (x[i].contains("<span class='searchmatch'>"))
					found = true;
				if (found) {
					information = x[i];
					if (x[i].contains("</div>")) {
						done = true;
					}
				}
			}
		}

		// Parse it and remove all tags etc.
		String endResult = information.replaceAll("\\<.*?\\>", "");

		endResult = endResult.trim().replaceAll("\\(redirect.+?\\)", "");
		endResult = endResult.trim().replaceAll("   +", " - ");
		endResult = endResult.trim().replaceAll("   ", " ");
		endResult = endResult.trim().replaceAll("  ", " ");

		// Get the URL with the same method as finding the information above
		String[] y = Pattern.compile("href=").split(information);

		boolean doneAgain = false;
		for (int i = 0; i < y.length; i++) {
			if (!doneAgain) {
				if (y[i].contains("/wiki/")) {
					doneAgain = true;
					URL = y[i];
				}
			}
		}

		// Trim everything down to just the URL
		URL = URL.trim().replaceFirst("/", "http://en.wikipedia.org/");
		URL = URL.trim().replaceAll("\"", "");
		int temp = URL.indexOf("title=");
		URL = URL.trim().substring(0, temp - 1);

		// Return the result
		return endResult + " - " + URL;
	}

	public String TBVsearch(String searchString) throws IOException {

		// search code

		// set all the strings that I will need in the extraction
		String str = "";
		String newString = "";
		String information = "";
		URL tbvURL = null;

		// get the URL to the Wiki-search and start all the writers and readers
		tbvURL = new URL("http://tbv.bsnet.se:80/index.php/" + searchString);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				tbvURL.openStream()));

		// Add <br> so that I have something to go on when I split it up later.
		while ((str = in.readLine()) != null) {
			str += "<br>";
			newString += str;
		}


		// Split the string up into an String array based on where you find the
		// <br> tag
		String[] x = Pattern.compile("<br>").split(newString);

		boolean done = false;
		boolean found = false;

		// Search for the most relevant information
		for (int i = 0; i < x.length; i++) {
			if (!done) {
				if (x[i].contains("<p>"))
					found = true;
				if (found) {
					information += x[i];
					if (x[i].contains("</p>")) {
						done = true;
					}
				}
			}
		}

		// Parse it and remove all tags etc.
		String endResult = information.replaceAll("\\<.*?\\>", "");

		endResult = endResult.trim().replaceAll("\\(redirect.+?\\)", "");
		endResult = endResult.trim().replaceAll("   +", " - ");
		endResult = endResult.trim().replaceAll("   ", " ");
		endResult = endResult.trim().replaceAll("  ", " ");

		if (endResult != "")
			return endResult + " - http://tbv.bsnet.se/index.php/"
					+ searchString;

		else
			return "Finns inte på TBV-Wiki =(";

	}

	public String returnURL(String searchString) throws IOException {
		String[] x = Pattern.compile(" ").split(searchString);

		boolean done = false;
		boolean found = false;
		String message = "";

		// Search for the most relevant information
		for (int i = 0; i < x.length; i++) {
			if (!done) {
				if (x[i].contains("http://") || x[i].contains("https://") )
					found = true;
				if (found) {
					message = x[i];
					done = true;
				}
			}
		}

		URL url = new URL(message);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));

		String str, newString = "" , information = "";

		// Add <br> so that I have something to go on when I split it up later.
		while ((str = in.readLine()) != null) {
			str += "<br>";
			newString += str;
		}

		// Split the string up into an String array based on where you find the <br> tag
		String[] y = Pattern.compile("<br>").split(newString);

		done = false;
		found = false;

		// Search for the most relevant information
		for (int i = 0; i < y.length; i++) {
			if (!done) {
				if (y[i].contains("<title>"))
					found = true;
				if (found) {
					information += y[i];
					if (y[i].contains("</title>")) {
						done = true;
					}
				}
			}
		}
		String endResult = null;
		String[] z = Pattern.compile("</title>").split(information);

		endResult = z[0];
		// Parse it and remove all tags etc.
		endResult = endResult.replaceAll("\\<.*?\\>", "");

		endResult = endResult.trim().replaceAll("   +", " ");
		endResult = endResult.trim().replaceAll("   ", " ");
		endResult = endResult.trim().replaceAll("  ", " ");
		if(message.contains("youtube")) {
			endResult = endResult.trim().replaceAll("- YouTube", "[YouTube]");
		}
		else if(message.contains("spotify")) {
			endResult = endResult.trim().replaceAll("on Spotify", "[Spotify]");
		}

		
		return endResult;
	}
}
