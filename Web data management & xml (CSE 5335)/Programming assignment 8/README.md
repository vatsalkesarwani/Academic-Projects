# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 8 : Storing XML in a Relational Database
MS in CS at University of Texas, Arlington

**Usage Example of SQLite JDBC jar:**

* javac Sample.java
* java -classpath ".:sqlite-jdbc-(VERSION).jar" Sample   # in Mac or Linux

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project8.html Also, alternatively given below.

**Description**

The purpose of this project is to learn how to store XML data in a relational database. This project must be done individually. No copying is permitted.

**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

Note that, if you use a Search Engine to find similar programs on the web, we will find these programs too. So don't do it because you will get caught and you will get an F in the course (this is cheating). Don't look for code to use for your project on the web or from other students (current or past). Just do your project alone using the help given in this project description and from your instructor only.

**Platform**

You will develop this project on Omega or your own PC/laptop. If you want use your own PC, you need to install [SQLite](http://sqlite.org/). Look at the file createDB.sql from project5. You need to create an sql schema in a file createCS.sql and create your database using:

sqlite3 mydb.sqlite  
.read createCS.sql  
.exit

**Project Requirements**

You will use the XML file [cs.xml](http://lambda.uta.edu/cse5335/spring14/examples/cs.xml) with the DTD [department.dtd](http://lambda.uta.edu/cse5335/spring14/examples/department.dtd). You need to parse this file and store it into a relational database and then convert the relational data back to XML.

* Create a relational schema from the department.dtd using the hybrid inlining method. Implement this schema using SQLite.
* Write a Java program that parses the cs.xml file using a SAX parser and stores the data into your relational database. Your program should be general enough to handle any XML document that matches this DTD.
* Write a java program that accesses your database only (not the XML data) and prints the data in the output in XML form. That is, the output of this program must be similar to the content of the cs.xml file (doesn't have to be exactly the same). 