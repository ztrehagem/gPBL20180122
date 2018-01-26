JAVAC = /usr/bin/javac
WEBINF = webapp/ROOT/WEB-INF/
CLASSPATH =${WEBINF}lib/servlet-api.jar:${WEBINF}lib/mysql-connector-java-5.1.45-bin.jar
ALLSOURCE = javasrc/*.java
SAVEPATH =  webapp/ROOT/WEB-INF/classes
all:
	${JAVAC} -classpath ${CLASSPATH} ${ALLSOURCE} -d ${SAVEPATH}

clean:
	rm -rf ${SAVEPATH}/*
