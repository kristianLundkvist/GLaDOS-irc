#!/bin/bash
wget http://www.jibble.org/files/pircbot-1.5.0.zip
unzip pircbot-1.5.0.zip
wget http://www.jibble.org/files/JMegaHal.jar
mkdir lib/
mv -v pircbot-1.5.0/pircbot.jar lib/
mv -v JMegaHal.jar lib/
chmod 755 -v lib/*
rm -v pircbot-1.5.0.zip
rm -rv pircbot-1.5.0
rm LICENCE.txt
rm CHANGELOG.txt