CC=javac
JVM=java
DEBUGGER=jdb
FLAGS=-g -d
DFLAGS=-cp ../bin -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
ATTACH=-connect com.sun.jdi.SocketAttach:hostname=localhost,port=8000
CLASS=hw5
SOURCE=*.java
TARGET=../bin
LIBS=
all:
	$(CC) $(FLAGS) $(TARGET) $(SOURCE) $(LIBS)
server:
	$(JVM) $(DFLAGS) $(CLASS)
debug:
	$(DEBUGGER) $(ATTACH)
