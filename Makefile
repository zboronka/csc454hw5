CC=javac
JVM=java
DEBUGGER=jdb
FLAGS=-g
DFLAGS=-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
ATTACH=-connect com.sun.jdi.SocketAttach:hostname=localhost,port=8000
CLASS=hw5
SOURCE=*.java
TARGET=
LIBS=
all:
	$(CC) $(FLAGS) $(SOURCE) $(TARGET) $(LIBS)
server:
	$(JVM) $(DFLAGS) $(CLASS)
debug:
	$(DEBUGGER) $(ATTACH)
