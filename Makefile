JAVAC = /usr/bin/javac
WEBINF = webapp/ROOT/WEB-INF
CLASSPATH = ${WEBINF}/lib/servlet-api.jar:${WEBINF}/lib/mysql-connector-java-5.1.45-bin.jar
ALLSOURCE = ${WEBINF}/classes/*.java
DIST = ${WEBINF}/classes

all:
	${JAVAC} -classpath ${CLASSPATH} -d ${DIST} ${ALLSOURCE}

clean:
	rm -rf ${WEBINF}/classes/*.class
