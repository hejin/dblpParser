
all: 
	mkdir -p conf journal
	javac -cp mmdb-2019-04-29.jar DblpParserConf.java
	javac -cp mmdb-2019-04-29.jar DblpParserJournal.java

run-conf: all
	java -Xmx32G -cp mmdb-2019-04-29.jar:. DblpParserConf dblp.xml dblp.dtd

run-journal: all
	java -Xmx32G -cp mmdb-2019-04-29.jar:. DblpParserJournal dblp.xml dblp.dtd

clean:
	rm *.class
