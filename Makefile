# This makefile will compile the wpv into an
# executable program. Options will include 
# compiling an executable and jar file

JAVAC = javac
JAVA = java
PROG = wpv
BIN = ./bin/
ARG = -Xlint:unchecked
SRC = ./src/
JTESTS = ./jtests/
MAIN = $(PROG)
JCP = `pwd`/junit4.10/junit-4.10.jar:`pwd`/junit4.10/
TDATA = ./tdata/
TDATAM = ./tdata/unreadable ./tdata/unreadabledir

all: build

build:
	mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(ARG) $(SRC)*.java

run: clean build
	$(JAVA) -classpath $(BIN) $(MAIN) $(TDATA)

test: clean build
	$(JAVAC) -cp $(SRC):$(JCP) -d $(BIN) $(JTESTS)*.java
	$(JAVA) -cp $(BIN):$(JCP) org.junit.runner.JUnitCore wpvtest
	$(JAVA) -cp $(BIN):$(JCP) org.junit.runner.JUnitCore FileUtiltest
	$(JAVA) -cp $(BIN):$(JCP) org.junit.runner.JUnitCore Tagstest
	$(JAVA) -cp $(BIN):$(JCP) org.junit.runner.JUnitCore ResultSetTest
	$(JAVA) -cp $(BIN):$(JCP) org.junit.runner.JUnitCore TagConfigtest
	$(JAVA) -cp $(BIN):$(JCP) org.junit.runner.JUnitCore StackPairtest

junit:
	$(JAVA) -cp `pwd`/junit4.10/junit-4.10.jar:`pwd`/junit4.10/ org.junit.runner.JUnitCore org.junit.tests.AllTests

clean:
	rm -rf $(BIN) $(TDATAM)
