<<<<<<< HEAD
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
=======
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
>>>>>>> ea3c7e904807735f4dfa9261180870283ab592cb
import java.util.Calendar;
import org.jibble.pircbot.*;
import org.jibble.jmegahal.*;


/**
 * GLaDOS, Genetic Lifeform and Disk Operation System, is an artificially intelligent computer system
 * used for monitoring IRC channels, performing tests on users and recording said users performance and information.
 * @author Bobby, Prosten
 *
 */
public class GLaDOS extends PircBot {

	private String [] quotes;
<<<<<<< HEAD
	MessageParse messageParse;
=======
	private JMegaHal hal;
	private boolean talkBack = false;
>>>>>>> ea3c7e904807735f4dfa9261180870283ab592cb
	
	/**
	 * Default constructor for GLaDOS
	 */
	public GLaDOS()
	{
<<<<<<< HEAD
		this.setName("GLaDOStest");
		
		messageParse = new MessageParse();
=======
		// Gives the bot the name GLaDOS
		this.setName("GLaDOS");
>>>>>>> ea3c7e904807735f4dfa9261180870283ab592cb
		
		// Creates a new MegaHal
		hal = new JMegaHal();
		
		//adds some phraces to GLaDOS
		hal.add("Hur är vädret idag?");
		hal.add("ja, jag lyder mästare!");
		
		// Loads an old brain into GLaDOS
		hal = Load();
		
			
		// Ugly hack until we've get an database up and running
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
			"There was even going to be a party for you. A big party that all your friends were invited to. I invited your best friend, the Companion Cube. Of course, he couldn't come because you murdered him. All your other friends couldn't come, either, because you don't have any other friends because of how unlikable you are. It says so right here in your personnel file: 'Unlikable. Liked by no one. A bitter, unlikable loner, whose passing shall not be mourned. Shall NOT be mourned.' That's exactly what it says. Very formal. Very official. It also says you were adopted, so that's funny, too.",
			"While it has been a faithful companion, your Companion Cube cannot accompany you through the rest of the test. If it could talk - and the Enrichment Center takes this opportunity to remind you that it cannot - it would tell you to go on without it, because it would rather die in a fire than become a burden to you. ", 
			"As part of a required Enrichment Center protocol, the previous statement that we would not monitor the test area was a complete fabrication. We will stop enhancing the truth in three... two... *zzzt* ",
			"Your entire life has been a mathematical error... a mathematical error I'm about to correct! ",
			"You are kidding me. Did you just stuff that Aperture Science thing-we-don't-know-what-it-does into an Aperture Science Emergency Intelligence Incinerator? ",
			"When I said 'deadly neurotoxin,' the 'deadly' was in massive sarcasm quotes. I could take a bath in this stuff. Put in on cereal, rub it right into my eyes. Honestly, it's not deadly at all... to *me.* You, on the other hand, are going to find its deadliness... a lot less funny.",
			"Well, you found me. Congratulations. Was it worth it? Because despite your violent behavior, the only thing you've managed to break so far... is my heart. Maybe you could settle for that, and we'll just call it a day. I guess we both know that isn't going to happen.",
			"Time out for a second. That wasn't supposed to happen. Do you see that thing that fell out of me? What is that? It's not the surprise. I've never seen it before! Never mind, it's a mystery I'll solve later... by myself, because you'll be dead. ",
			"The Enrichment Center promises to always provide a safe testing environment. In dangerous testing environments, the Enrichment Center promises to always provide useful advice. For instance: the floor here will kill you. Try to avoid it.",
			"Look, you're wasting your time. And, believe me, you don't have a whole lot left to waste. What's your point, anyway? *Survival?* Well, then, the last thing you want to do is hurt me. I have your brain scanned and permanently backed-up in case something terrible happens to you, which it's just about to. Don't believe me? Here, I'll put you on: *Strange voice: Hellooooooooo!* That's you! That's how dumb you sound! You've been wrong about every single thing you've ever done, including this thing. You're not smart. You're not a scientist. You're not a doctor. You're not even a full-time employee! Where did your life go so wrong? ",
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
			"We are pleased that you made it through the final challenge where we pretended we were going to murder you. We are very very happy for your success. We are throwing a party in honor of your tremendous success. Place the device on the ground, then lie on your stomach with your arms at your sides. A party associate will arrive shortly to collect you for your party. Make no further attempt to leave the testing area. Assume the Party Escort Submission Position, or you will miss the party. ",
			"This is your fault. It didn't have to be like this. I'm not kidding, now! Turn back, or I *will* kill you! I'm going to kill you, and all the cake is gone! You don't even care, do you? This is your last chance! ",
			"I think we can put our differences behind us... for science... you monster.",
			"Oh, it's you.. It's been a long time. How have you been? I've been *really* busy being dead. You know, after you *murdered* me! ",
			"Oh. Hi. So. How are you holding up? BECAUSE I'M A POTATO! Oh good. My slow clap processor made it into this thing. So we have that. Since it doesn't look like we're going anywhere... Well, we are going somewhere. Alarmingly fast, actually. But since we're not busy other than that, here's a couple of facts. He's not just a regular moron. He's the product of the greatest minds of a generation working together with the express purpose of building the dumbest moron who ever lived. And you just put him in charge of the entire facility. *clap clap* Good, that's still working. Hey, just in case this pit isn't actually bottomless, do you think maybe you could unstrap one of those long fall boots of yours and shove me into it? Just remember to land on one foot...",
			"Well done. Here are the test results: You are a horrible person. I'm serious, that's what it says: A horrible person. We weren't even testing for that. Don't let that 'horrible person' thing discourage you. It's just a data point. If it makes you feel any better, science has now validated your birth mother's decision to abandon you on a doorstep. ",
			"Most people emerge from suspension terribly undernourished. I want to congratulate you on beating the odds and somehow managing to pack on a few pounds. ",
			"I hope you brought something stronger than a portal gun this time. Otherwise, I'm afraid you're about to become the immediate past president of the Being Alive club. Ha ha.",
			"Clave Johnson: Alright, I've been thinking, when life gives you lemons, don't make lemonade! | GLaDOS: Yeah. | Cave Johnson: Make life take the lemons back! | GLaDOS: Yeah! | Cave Johnson: Get Mad! | GLaDOS: Yeah! | Cave Johnson: I don't want your damn lemons, what am I supposed to do with these? | GLaDOS: Yeah. take the lemons! | Cave Johnson: Demand to see life's manager! Make life rue the day it thought it could give Cave Johnson lemons! Do you know who I am? I'm the man whose gonna burn your house down... with the lemons! | GLaDOS: Oh, I like this guy. | Cave Johnson: I'm gonna get my engineers to invent a combustible lemon that'll burn your house down. | GLaDOS: Burn it down! Burning people. He says what we're all thinking."
		};
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
<<<<<<< HEAD
		if(this.messageParse.isCommand(message)){
			String[] command = this.messageParse.parseString(message);

			if(command[0].equalsIgnoreCase("time"))
			{
				int hours;
				int minutes;

				Calendar now = Calendar.getInstance();

				hours = now.get(Calendar.HOUR_OF_DAY);
				minutes = now.get(Calendar.MINUTE);
				String time = "";
				time = hours + ":" + minutes;


				sendMessage(channel, sender + ": The time is now: " + time);
				sendMessage(channel, sender + ": Sometime tomorrow, there will be cake.");
			}

			if(command[0].equalsIgnoreCase("GLaDOS")) 
			{
				int random = (int) (Math.random()*this.quotes.length);

				sendMessage(channel, this.quotes[random]);
			}

		}
=======
		String lmessage = message.toLowerCase();
		
		if(lmessage.startsWith("!"))
		{
			if(message.equalsIgnoreCase("!time"))
			{
				int hours;
				int minutes;
			
				// Creates a Calendar to "pull" the current time from
				Calendar now = Calendar.getInstance();
			
				// Gets the time in Hours and minutes
				hours = now.get(Calendar.HOUR_OF_DAY);
				minutes = now.get(Calendar.MINUTE);
				String time = "";
			
				// Puts the Integers into one formatted string
				time = (Integer.toString(hours) + ":" + Integer.toString(minutes));

				// Sends the current time into the channel, along with a sneaky message
				sendMessage(channel, " The time is: " + time);
			
			}
		
			if(message.equalsIgnoreCase("!GLaDOS")) 
			{
				// Generates a random number based on the quote-array's length
				int random = (int) (Math.random()*(double)this.quotes.length);

				// Sends the quote to the channel
				sendMessage(channel, this.quotes[random]);
			}
			
			// For activation and Deactivation of the response function
			if(message.equalsIgnoreCase("!talkback"))
			{
				setTalkBack(!isTalkBackEnabled());
			}
			
			//saves GLaDOS's "brain"
			if(message.equalsIgnoreCase("!save"))
			{
				Save(hal);
			}
		}
		
		else
		{ 
			// Checks if the message contains "GLaDOS"
			if(message.toLowerCase().indexOf(this.getNick().toLowerCase()) >= 0) 
			{
				// Replies if it does and "TalkBack" is enabled
				if(isTalkBackEnabled())
				{
					String reply = hal.getSentence();
					sendMessage(channel, reply);
				}
				else
				{
					// If the message isn't to her, she saves the message and saves her "brain"
					hal.add(message);
					Save(hal);
				}
			}
			// If the message isn't to GLaDOS
			else
			{
				hal.add(message);
				Save(hal);
			}
		}
		
		
		
	}
	
	
	public void OnRemove()
	{
		Save(hal);
		hal = null;
	}
	
	// Save GLaDOS to file
	public static void Save(JMegaHal obj)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream("GLaDOS.obj");
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
>>>>>>> ea3c7e904807735f4dfa9261180870283ab592cb
	}
	
	// Load GLaDOS from file
	public static JMegaHal Load()
	{
        FileInputStream fis;
        ObjectInputStream in;
        JMegaHal obj = null;
        //
        try{
            fis = new FileInputStream("GLaDOS.obj");
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
        return obj;
	}
	
	public boolean isTalkBackEnabled()
	{
		return talkBack;
	}
	
	public void setTalkBack(boolean talkBack)
	{
		this.talkBack = talkBack;
	}

}
