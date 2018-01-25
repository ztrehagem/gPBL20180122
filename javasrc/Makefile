JAVAC = /usr/bin/javac
CLASSPATH =./lib/servlet-api.jar:./lib/mysql-connector-java-5.1.45-bin.jar
ALLSOURCE = *.java
SAVEPATH =  ../webapp/ROOT/WEB-INF/classes/
all: 
	${JAVAC} -classpath ${CLASSPATH} ${ALLSOURCE} -d ${SAVEPATH}

clean:
	rm -rf *.class
