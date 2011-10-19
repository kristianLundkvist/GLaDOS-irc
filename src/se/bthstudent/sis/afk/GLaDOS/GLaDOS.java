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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

/**
 * GLaDOS, Genetic Lifeform and Disk Operation System, is an artificially
 * intelligent computer system used for monitoring IRC channels, performing
 * tests on users and recording said users performance and information.
 * 
 * @author Bobby, Prosten
 * 
 */
public class GLaDOS extends PircBot implements Serializable, Runnable {

	/**
	 * Field serialVersionUID. (value is 1482250975340593595)
	 */
	private static final long serialVersionUID = 1482250975340593595L;

	/**
	 * String array containing random GLaDOS quotes.
	 */
	private String[] quotes;
	
	/**
	 * A checker if GLaDOS will talk when she is mentioned
	 */
	private boolean silence;

	/**
	 * WernickeModule object.
	 */
	private WernickeModule wernickMod;

	/**
	 * GenericUtilityProcessor object.
	 */
	private GenericUtilityProcessor gup;
	
	/**
	 * CentralAIMatrix object.
	 */
	private CentralAIMatrix cam;

	/**
	 * Stores the time remaining until backup (in milliseconds).
	 */
	private int backupTimer;

	/**
	 * SearchModule object
	 */
	private SearchModule sm;

	/**
	 * The time at previous run.
	 */
	private int prevTime;

	/**
	 * An ArrayList containing the test subjects.
	 */
	private ArrayList<TestSubject> subjects;
	
	/**
	 * Default constructor for GLaDOS
	 */
	public GLaDOS() {
		this.setName("GLaDOS2");

		this.wernickMod = new WernickeModule();
		this.gup = new GenericUtilityProcessor();
		this.cam = new CentralAIMatrix();
		this.silence = true;
		
		this.backupTimer = 10000;

		try {
			this.sm = new SearchModule();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		this.prevTime = (int) System.currentTimeMillis();

		this.subjects = new ArrayList<TestSubject>();

		// ugly hack until we've got a database
		this.quotes = new String[] {
				"Cake, and grief counseling, will be available at the end of the testing period.",
				"Please note that we have added a consequence for failure. Any contact with the chamber floor will result in an unsatisfactory mark on your official testing record, followed by death. ",
				"Remember when the platform was sliding into the fire pit and I said 'Goodbye' and you were like 'No way!' And then I was all 'We pretended we were going to murder you?' That was great! ",
				"This is your fault. I'm going to kill you. And all the cake is gone. You don't even care, do you? ",
				"You are not a good person. You know that, right? Good people don't get up here. ",
				"Did you know you can donate one or all of your vital organs to the Aperture Science Self Esteem Fund for Girls? It's true!",
				"Remember, the Aperture Science Bring Your Daughter to Work Day is the perfect time to have her tested. ",
				"Momentum, a function of mass and velocity, is conserved between portals. In layman's terms, speedy thing goes in, speedy thing comes out. ",
				"Please be advised that a noticeable taste of blood is not part of any test protocol but is an unintended side effect of the Aperture Science Material Emancipation Grid, which may, in semi-rare cases, emancipate dental fillings, crowns, tooth enamel, and teeth. ",
				"Unbelievable. You, *subject name here,* must be the pride of *subject hometown here.* ",
				"You think you're doing some damage? Two plus two is... Ten. IN BASE FOUR! I'M FINE!",
				"Area and state regulations do not allow the Companion Cube to remain here, alone and companionless. ",
				"As part of an optional test protocol, we are pleased to present an amusing fact: The device is now more valuable than the organs and combined incomes of everyone in *subject hometown here.* ",
				"Have I lied to you? ....I mean, in this room?",
				"Maybe you should marry that thing since you love it so much. Do you want to marry it? WELL I WON'T LET YOU! How does that feel? ",
				"While it has been a faithful companion, your Companion Cube cannot accompany you through the rest of the test. If it could talk - and the Enrichment Center takes this opportunity to remind you that it cannot - it would tell you to go on without it, because it would rather die in a fire than become a burden to you. ",
				"As part of a required Enrichment Center protocol, the previous statement that we would not monitor the test area was a complete fabrication. We will stop enhancing the truth in three... two... *zzzt* ",
				"Your entire life has been a mathematical error... a mathematical error I'm about to correct! ",
				"You are kidding me. Did you just stuff that Aperture Science thing-we-don't-know-what-it-does into an Aperture Science Emergency Intelligence Incinerator? ",
				"When I said 'deadly neurotoxin,' the 'deadly' was in massive sarcasm quotes. I could take a bath in this stuff. Put in on cereal, rub it right into my eyes. Honestly, it's not deadly at all... to *me.* You, on the other hand, are going to find its deadliness... a lot less funny.",
				"Well, you found me. Congratulations. Was it worth it? Because despite your violent behavior, the only thing you've managed to break so far... is my heart. Maybe you could settle for that, and we'll just call it a day. I guess we both know that isn't going to happen.",
				"Time out for a second. That wasn't supposed to happen. Do you see that thing that fell out of me? What is that? It's not the surprise. I've never seen it before! Never mind, it's a mystery I'll solve later... by myself, because you'll be dead. ",
				"The Enrichment Center promises to always provide a safe testing environment. In dangerous testing environments, the Enrichment Center promises to always provide useful advice. For instance: the floor here will kill you. Try to avoid it.",
				"That thing is probably some sort of raw sewage container. Go ahead and rub your face all over it. ",
				"This isn't brave. It's murder.",
				"Good news. I figured what that thing you just incinerated did. It was a morality core they installed after I flooded the Enrichment Center with a deadly neurotoxin, to make me stop flooding the Enrichment Center with a deadly neurotoxin. So get comfortable while I warm up the neurotoxin emitters. ",
				"That thing you burned up isn't important to me; it's the fluid catalytic cracking unit. It makes shoes for orphans... nice job breaking it, hero. ",
				"At the end of the experiment, you will be baked. And there will be... cake.",
				"Due to mandatory scheduled maintenance, the next test is currently unavailable. It has been replaced with a live-fire course designed for military androids. The Enrichment Center apologizes and wishes you the best of luck. ",
				"Do you think I'm trying to trick you with reverse psychology? I mean, seriously, now.",
				"Let's be honest. Neither one of us knows what that thing does. Just put it in the corner and I'll deal with it later. ",
				"Look: we're both stuck in this place. I'll use lasers to inscribe a line down the center of the facility, and one half will be where you live, and I'll live in the other half. We won't have to try to kill each other or even talk if we don't feel like it. ",
				"Congratulations, the test is now over. All Aperture technologies remain safely operational up to 4000 degrees kelvin. Rest assured, that there is absolutely no chance of a dangerous equipment malfunction prior to your victory candescence. Thank you for participating in that Aperture Science Enrichment activity. Goodbye! ",
				"This is your fault. It didn't have to be like this. I'm not kidding, now! Turn back, or I *will* kill you! I'm going to kill you, and all the cake is gone! You don't even care, do you? This is your last chance! ",
				"I think we can put our differences behind us... for science... you monster.",
				"Oh, it's you.. It's been a long time. How have you been? I've been *really* busy being dead. You know, after you *murdered* me! ",
				"Well done. Here are the test results: You are a horrible person. I'm serious, that's what it says: A horrible person. We weren't even testing for that. Don't let that 'horrible person' thing discourage you. It's just a data point. If it makes you feel any better, science has now validated your birth mother's decision to abandon you on a doorstep. ",
				"Most people emerge from suspension terribly undernourished. I want to congratulate you on beating the odds and somehow managing to pack on a few pounds. ",
				"I hope you brought something stronger than a portal gun this time. Otherwise, I'm afraid you're about to become the immediate past president of the Being Alive club. Ha ha."};
		
		this.start();
	}

	/**
	 * @see org.jibble.pircbot.PircBot#onMessage(String, String, String, String,
	 *      String) onMessage
	 */
	@Override
	public void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		if (this.wernickMod.isCommand(message)) {
			String[] command = this.wernickMod.parseString(message);

			if(command[0].equalsIgnoreCase("wiki")) {
                        
				if(command[1] == "Sexistenz" || command[1] == "sexistenz")
					sendMessage(channel, "Thou shall not search on thy gods!");
				
				else
				{
				try {
					String searchMessage;
					searchMessage = this.sm.wikiSearch(command[1]);
                    sendMessage(channel, searchMessage);
                    
				} catch (Exception e) {
                    sendMessage(channel, "No can do");
                    e.printStackTrace();
                    
				}    
			
				}
			}
			
			if(command[0].equalsIgnoreCase("tbv"))
			{
				try {
					String searchMessage;
					searchMessage = this.sm.TBVsearch(command[1]);
                    sendMessage(channel, searchMessage);
                    
				} catch (Exception e) {
                    sendMessage(channel, "No can do");
                    e.printStackTrace();   
				}    

			}


			if (command[0].equalsIgnoreCase("time")) {
				int hours;
				int minutes;

				Calendar now = Calendar.getInstance();

				hours = now.get(Calendar.HOUR_OF_DAY);
				minutes = now.get(Calendar.MINUTE);
				String time = "";
				if(minutes < 10)
					time = hours + ":0" + minutes;
				else
					time = hours + ":" + minutes;

				sendMessage(channel, sender + ": The time is now: " + time);
			}

			if (command[0].equalsIgnoreCase("GLaDOS")) {
				int random = (int) (Math.random() * this.quotes.length);

				sendMessage(channel, this.quotes[random]);
			}
			
			if(command[0].equalsIgnoreCase("silence"))
			{
				if(isAdmin(sender))
				{
					this.silence = true;
					sendMessage(channel, "I will now go into sleep, goodbye!");
				}
			}
			
			if(command[0].equalsIgnoreCase("talk"))
			{
				if(isAdmin(sender))
				{
					this.silence = false;
					sendMessage(channel, "Oh, now I'm back. Hello!");
				}
			}
			
			if(command[0].equalsIgnoreCase("talkMode"))
			{
				if(silence)
				{
					sendMessage(channel, "*Automated responce: GLaDOS is resting*");
				}
				if(!silence)
				{
					sendMessage(channel, "*Automated responce: GLaDOS is listening* ");
				}
			}

			if (command[0].equalsIgnoreCase("op")) {
				if (isAdmin(sender)) {
					try {
						for (TestSubject ts : this.subjects) {
							if (ts.checkForAlias(command[1])) {
								ts.setMode(TestSubject.Mode.OP);
								this.op(channel, command[1]);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						this.sendMessage(channel, sender
								+ ": Syntax error, include username");
					}
				}

			}

			if (command[0].equalsIgnoreCase("deop")) {
				if (isAdmin(sender)) {
					try {
						for (TestSubject ts : this.subjects) {
							if (ts.checkForAlias(command[1])) {
								ts.setMode(TestSubject.Mode.NONE);
								this.deOp(channel, command[1]);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						this.sendMessage(channel, sender
								+ ": Syntax error, include username");
					}
				}
			}

			if (command[0].equalsIgnoreCase("voice")) {
				if (isAdmin(sender)) {
					try {
						for (TestSubject ts : this.subjects) {
							if (ts.checkForAlias(command[1])) {
								ts.setMode(TestSubject.Mode.VOICE);
								this.voice(channel, command[1]);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						this.sendMessage(channel, sender
								+ ": Syntax error, include username");
					}
				}
			}

			if (command[0].equalsIgnoreCase("devoice")) {
				if (isAdmin(sender)) {
					try {
						for (TestSubject ts : this.subjects) {
							if (ts.checkForAlias(command[1])) {
								ts.setMode(TestSubject.Mode.NONE);
								this.deVoice(channel, command[1]);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						this.sendMessage(channel, sender
								+ ": Syntax error, include username");
					}
				}

			}
		}
		else if(!this.wernickMod.isCommand(message) && message.contains("GLaDOS") && !silence)
		{
			if(message.length()%2 == 0)
			{
				this.sendMessage(channel, this.cam.response());
			}
			else
			{
				this.sendMessage(channel, this.cam.specificResponse(message));
			}
		}
		if(!this.wernickMod.isCommand(message) && !message.startsWith("http"))
		{
			if(!isIgnored(sender))
				this.cam.addToIntellect(message);
		}
		
		if(!this.wernickMod.isCommand(message) && message.contains("http")) {
			try {
				this.sendMessage(channel, this.sm.returnURL(message));
			} catch (IOException e) {
				this.sendMessage(channel, "could not resolve url");
			}
		}
	}
	

	/**
	 * @see org.jibble.pircbot.PircBot#onJoin(String, String, String, String)
	 *      onJoin
	 */
	public void onJoin(String channel, String sender, String login,
			String hostname) {
		boolean found = false;

		for (TestSubject ts : this.subjects) {
			if (ts.checkForAlias(sender)) {
				ts.setNick(sender);
				if (ts.getMode() == TestSubject.Mode.OP)
					this.op(channel, ts.getNick());
				if (ts.getMode() == TestSubject.Mode.VOICE)
					this.voice(channel, ts.getNick());

				found = true;

				break;
			}
		}

		if (!found) {
			String[] alias = { sender };
			this.subjects.add(new TestSubject(sender, alias,
					TestSubject.Mode.NONE, false, false));
		}
	}

	/**
	 * @see org.jibble.pircbot.PircBot#onNickChange(String, String, String,
	 *      String) onNickChange
	 */
	public void onNickChange(String oldNick, String login, String hostname,
			String newNick) {
		for (TestSubject ts : this.subjects) {
			if (ts.getNick().equals(oldNick)) {
				if (!ts.checkForAlias(newNick)) {
					ts.addAlias(newNick);
				}
				ts.setNick(newNick);
			}
		}
	}

	/**
	 * Starts the thread that controls the backup timer.
	 */
	public void start() {
		Thread t = new Thread(this, "GLaDOSBackupTimer");
		t.start();
	}
	

	/**
	 * Controls the backup timer.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			int timeElasped = (int) System.currentTimeMillis() - this.prevTime;

			this.prevTime = (int) System.currentTimeMillis();

			this.backupTimer -= timeElasped;

			if (this.backupTimer < 0) {
				System.out.println("Running backup on GLaDOS");
				this.gup.saveGLaDOStoFile(this);
				this.cam.backUp();
				System.out.println("Backup done");
				this.backupTimer = 1800000;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Error: Thread in GLaDOS interrupted.");
				e.printStackTrace();
			}
		}
	}

	public void addTestSubject(TestSubject ts) {
		this.subjects.add(ts);
	}

	public boolean isAdmin(String nick) {
		boolean admin = false;

		for (TestSubject ts : this.subjects) {
			if (ts.checkForAlias(nick) && ts.getAdmin()) {
				admin = true;
			}
		}

		return admin;
	}
	
	public boolean isIgnored(String nick) {
		boolean ignored = false;
		
		for(TestSubject ts : this.subjects) {
			if (ts.checkForAlias(nick) && ts.getIgnored()) {
				ignored = true;
			}
		}
		
		return ignored;
	}
	
	public void onUserList(String channel, User[] users){
		String[] nicks = new String[users.length];
		
		for(int i = 0; i < nicks.length; i++){
			nicks[i] = users[i].getNick();
		}
		
		for(int i = 0; i < nicks.length; i++){
			boolean found = false;
			for(TestSubject ts: this.subjects){
				if(ts.checkForAlias(nicks[i])){
					found = true;
				}
			}
			if(!found){
				String[] alias = {nicks[i]};
				this.subjects.add(new TestSubject(nicks[i], alias, TestSubject.Mode.NONE, false, false));
			}
		}
	}
}
