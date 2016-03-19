# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 7 : Using XQuery
MS in CS at University of Texas, Arlington

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project7.html Also, alternatively given below.

**Description**

The purpose of this project is to learn XQuery. This project must be done individually. No copying is permitted.

**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

Note that, if you use a Search Engine to find similar programs on the web, we will find these programs too. So don't do it because you will get caught and you will get an F in the course (this is cheating). Don't look for code to use for your project on the web or from other students (current or past). Just do your project alone using the help given in this project description and from your instructor only.

**Platform**

You will do this project on your own PC/laptop. You will use Zorba, which is a free implementation of XQuery.

* [Download Zorba](http://www.zorba.io/download). It provides binaries for Windows, Linux, and Mac OS. 

Put all XQueries in a file "queries.xq" and use the [Zorba Command Line Utility](http://www.zorba.io/documentation/latest/zorba/cli) to evaluate the XQueries.

**Documentation**

The following provide some tutorials. Use them as a reference only.

* [Zorba 3.0 Documentation](http://www.zorba.io/documentation/latest)
* [XQuery: A Query Language for XML](http://lambda.uta.edu/cse5335/spring13/sigmod03_xquery.pdf)
* [XQuery: A Guided Tour ](http://lambda.uta.edu/cse5335/spring13/Katz_xquery.pdf)

**Project Requirements**

Consider the following XML document along with its DTD that describes auctions:

[auction.zip](http://lambda.uta.edu/cse5335/spring14/auction.zip) (the zipped XML document)

It contains synthetic data (automatically generated). The words for text paragraphs are taken from Shakespeare's plays. There is also a [DTD](http://lambda.uta.edu/cse5335/spring14/auction.dtd) (the link is not broken; use Save As to save it) for the XML file but it is not very useful. Express the following queries using XQuery and run them against the file auction.xml using Zorba:

1. Print the number of items listed on all continents.
2. List the names of items registered in Europe along with their descriptions.
3. List the names of persons and the number of items they bought.
4. List all persons according to their interest (ie, for each iterest category, display the persons on that category).
5. Group persons by their categories of interest and output the size of each group.
6. List the names of persons and the names of the items they bought in Europe.
7. Give an alphabetically ordered list of all items along with their location.
8. List the reserve prices of those open auctions where a certain person with id person3 issued a bid before another person with id person6. 