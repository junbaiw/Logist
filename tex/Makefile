#
#  This sample makefile provides a starting point for building
#  HTML documents which multiple segments and inter-segment
#  dependencies.  This example is included with the V96.1
#  version of latex2html.
#

TOP 	  = Logist
INT	  = internals.pl
TOPTITLE  = "Logist v1.0"

L2H 	  = latex2html -show_section_numbers
LaTeX	  = latex
DVIPS	  = dvips
BIBTEX    = bibtex

LTOP	  = $(L2H) -info "" -t $(TOPTITLE) $(TOP).tex

all:	$(TOP).ps $(TOP)/$(TOP).html

$(TOP).dvi:	$(TOP).tex $(TOP).aux
	$(LaTeX) $(TOP).tex

$(TOP).aux:	$(TOP).tex
	$(LaTeX) $(TOP).tex

$(TOP).ps:	$(TOP).dvi
	$(DVIPS) -o $@ $(TOP)

$(TOP)/$(TOP).html :	$(TOP).tex $(TOP).aux
	$(LTOP)

clean:	
	rm -rf $(TOP) $(TOP).dvi $(TOP).aux $(TOP).log $(TOP).toc \
	    $(TOP).lof $(TOP).bbl $(TOP).blg $(TOP).ps *~ auto

