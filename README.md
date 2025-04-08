# dblpParser

## What
* Get Titles and Abstracts of IEEE/ACM journal & conference papers
  
## How
* parse local DBLP xml file to get paper titles and DOIs.
 * download DBLP xml and dtd from DBLP site - cd dblp subdir and run 'make download'
 * run 'make run-conf' to parse dblp xml to get the conference paper info (title and DOI - if any)
  * 2 type files to be generated : one contains the titles and the other contains the associated DOI (if any)
  * necessary cleanup needed for the DOI file (containing non-DOI information)
 * run 'make run-journal' to parse dblp xml to get the journal paper info (title and DOI)
  * 2 type files to be generated : one contains the titles and the other contains the associated DOI 
  * necessary cleanup needed for the DOI file (containing non-DOI information)
* use semanticscholar python library to retrieve the paper abstracts.
 * pip install semanticscholar
 * run 'python3 fetch-abstract conf|journal conf-name|journal-name' to fetch the abstract
  * validate the fetch with the title against 

## NOTE
* please do limit the semanticscholar API rate : < 100 request per 5 mins.

## TODO
Too many things to do.

## Refs
* https://semanticscholar.readthedocs.io/en/latest/
* https://dblp.org/faq/1474681.html
