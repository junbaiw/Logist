#========================================================================== #
#                                                                           #
#    Copyright (96)  Hartmut S. Loos                                        #
#                                                                           #
#    Institut f"ur Neuroinformatik   ND 03                                  #
#    Ruhr-Universit"at Bochum                                               #
#    44780 Bochum                                                           #
#                                                                           #
#    Tel  : +49 234 7007970                                                 #
#    Email: loos@neuroinformatik.ruhr-uni-bochum.de                         #
#                                                                           #
#========================================================================== #
#
#	Makefile for Logist.java	
#
#	'Compile' Logist.java:
#		make
#	Delete all unnecessary files:
#		make clean
#	tar all necessary files in a file in the upper directory:
#		make tar
#       Generate documentation in subdirectory ./tex/:
#               make docu


JAVA_SRC		= Logist
ARCHIVE			= ../Logist.tar.gz
TEX                     = ./tex
DIRS			= . ./tex
FILES			= *.java *.html *.class *.zip Makefile COPYING \
                          tex/* tex/Logist/*
ARCHIVE			= *.gz
HOMEADDRESS		= hartmut@cactus.ruhr.de
UNIADDRESS		= loos@neuroinformatik.ruhr-uni-bochum.de

#
# Binaries
#
JAVAC		= javac
JAVADOC		= javadoc
TAR		= tar
RM		= rm
MAIL		= elm
CHMOD		= chmod
TCLSH		= tclsh
ZIP		= zip

all:
		$(JAVAC) $(JAVA_SRC).java
		$(ZIP) $(JAVA_SRC).zip *.class

tar:
		$(TAR) czvf $(ARCHIVE) *

docu:
		cd $(TEX) ; make

clean:
		$(RM) -f *.class *~ *.zip
		cd $(TEX) ; make clean

mailHome:	clean tar
		uuencode $(ARCHIVE) $(ARCHIVE) | $(MAIL) $(HOMEADDRESS) 
		make world

mailUni:	clean tar
		uuencode $(ARCHIVE) $(ARCHIVE) | $(MAIL) $(UNIADDRESS) 
		make world

world:	all docu
		$(CHMOD) a+r $(FILES)
		$(CHMOD) 755 $(DIRS)
		$(CHMOD) 644 $(ARCHIVE)

