
all: 
	mkdir -p conf journal
	javac -cp mmdb-2019-04-29.jar DblpParserConf.java
	javac -cp mmdb-2019-04-29.jar DblpParserJournal.java

run-conf: all
	java -Xmx32G -cp mmdb-2019-04-29.jar:. DblpParserConf dblp.xml dblp.dtd

clean-conf-doi: 
	cd conf && bash cleanup.sh

run-journal: all
	java -Xmx32G -cp mmdb-2019-04-29.jar:. DblpParserJournal dblp.xml dblp.dtd

clean-journal-doi: 
	cd journal && bash cleanup.sh

download:
	wget https://dblp.org/xml/dblp.xml.gz
	gunzip dblp.xml.gz
	wget https://dblp.org/xml/dblp.dtd

clean:
	rm *.class
