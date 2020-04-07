#!/bin/bash
#
# Goal
#
#		Installer java
#
# Usage:
#
#	 	 sudo ./installJava.sh 
#
# Perimetre:
#
#		Tester sous ubuntu 18.04
#
# Prerequis
#
#		Lisez aussi le fichier voisin : tips.txt

# -------------------------------------------
# 			java 8
# -------------------------------------------

#sudo add-apt-repository ppa:webupd8team/java
#sudo apt-get update
#sudo apt-get install oracle-java8-installer
#sudo apt-get install oracle-java8-set-default

# -------------------------------------------
# 			java 11
# -------------------------------------------

sudo apt-get update
sudo apt install openjdk-11-jdk

# -------------------------------------------
# 			check
# -------------------------------------------

echo ""
java -version
echo ""

# -------------------------------------------
# 			end
# -------------------------------------------

