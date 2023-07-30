#Makefile defined to automate compilation of the java classes 
#Author: Kananelo Chabeli
#Date: 30/07/2023

#specify location to Java compiler
JAVAC= /usr/bin/javac
#define variables
BINDIR=bin # directory where the compiled class files will be stored
SRCDIR=src # directory where source files are
#rule for making class files
$(BINDIR)/%.class: $(SRCDIR)/%.java
  $(JAVAC) -d $(BINDIR) -cp $(BINDIR) $<

#List of classe files according to thier dependency
CLASSES= TerrianArea.class Search.class MonteCarloMinimization.class MonteCarloMinimizationParallel.class Runner.class

#link these classes to files in bin directory(compiled code)
CLASS_FILES= $(CLASSES:%.class=$(BINDIR)/%.class)
#defualt rule
build: $(CLASS_FILES)

run: $(CLASS_FILES)
  	java -cp $(BINDIR) Runner $(filter-out $@,$(MAKECMDGOALS))
serial: $(CLASS_FILES)
  	java -cp $(BINDIR) MonteCarloMinimization $(filter-out $@,$(MAKECMDGOALS))
parallel: $(CLASS_FILES)
  	java -cp $(BINDIR) MonteCarloMinimizationParallel $(filter-out $@,$(MAKECMDGOALS))
clean:
  rm -rf $(BINDIR)/*.class
