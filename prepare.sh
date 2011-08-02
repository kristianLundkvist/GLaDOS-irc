#!/bin/bash
wget http://www.jibble.org/files/pircbot-1.5.0.zip
unzip pircbot-1.5.0.zip
wget http://jwpl.googlecode.com/files/jwpl_v05b.jar
wget http://www.jibble.org/files/JMegaHal.jar
wget http://downloads.sourceforge.net/project/jwbf/jwbf%20active%20releases/jwbf-1.3.3-298/jwbf-1.3.3-298-src.tar.gz
gunzip jwbf-1.3.3-298-src.tar.gz
tar xvf jwbf-1.3.3-298-src.tar 
mv -v pircbot-1.5.0/pircbot.jar lib/
mv -v jwpl_v05b.jar lib/
mv -v JMegaHal.jar lib/
chmod 755 -v lib/*
rm -v pircbot-1.5.0.zip
rm -rv src/
rm -rv test/
rm -v jwbf-1.3.3-298.src.tar
rm LICENCE.txt
rm CHANGELOG.txt