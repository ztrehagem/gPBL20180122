.SUFFIXES: .java .class
JAVAC = /usr/bin/javac
CLASSPATH =../lib/servlet-api.jar:../lib/mysql-connector-java-5.1.45-bin.jar
ALLSOURCE = *.java

all: 
	${JAVAC} -classpath ${CLASSPATH} ${ALLSOURCE} 

clean:
	rm -rf *.class
