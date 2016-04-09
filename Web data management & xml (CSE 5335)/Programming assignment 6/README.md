# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 6 : Using XPath, DOM and SAX
MS in CS at University of Texas, Arlington

**Usage Instructions:**

* Download and put all these files in a folder
* Use the following command to compile the java programs: javac Sample.java
* Use the following command to run the java programs: java Sample

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project6.html Also, alternatively given below.

**Description**

The goal of this project is to learn XPath, DOM, and SAX to query XML data. This project must be done individually. No copying is permitted.

**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

Note that, if you use a Search Engine to find similar programs on the web, we will find these programs too. So don't do it because you will get caught and you will get an F in the course (this is cheating). Don't look for code to use for your project on the web or from other students (current or past). Just do your project alone using the help given in this project description and from your instructor only.

**Platform**

You will do this project on your own PC/laptop. You may use a text editor to develop your Java programs but you may use an IDE, such as Eclipse or Netbeans, if you want.

Here are some examples:

* [An XPath query evaluated in Java](http://lambda.uta.edu/cse5335/spring14/examples/xpath.java)
* [A simple example of DOM in Java](http://lambda.uta.edu/cse5335/spring14/examples/simple-dom.java)
* [A simple example of SAX in Java](http://lambda.uta.edu/cse5335/spring14/examples/simple-sax.java)
* [The cs.xml XML document used in Java](http://lambda.uta.edu/cse5335/spring14/examples/cs.xml)

**Documentation**

The following web pages provide some tutorials. Use them as a reference only.

* [XPath Tutorial](http://www.zvon.org/xxl/XPathTutorial/General/examples.html)
* [Another XPath Tutorial](http://www.w3schools.com/xpath/default.asp)
* [Java API for javax.xml.xpath](http://java.sun.com/javase/6/docs/api/javax/xml/xpath/package-summary.html)
* [XML DOM Tutorial](http://www.w3schools.com/dom/default.asp)
* [DOM Java binding](http://www.w3.org/TR/DOM-Level-2-Core/java-binding.html)
* [Package org.w3c.dom](http://java.sun.com/javase/6/docs/api/org/w3c/dom/package-summary.html)
* [Package org.xml.sax](http://java.sun.com/javase/6/docs/api/org/xml/sax/package-summary.html)

**Project Requirements**

First, download the following XML document along with its DTD that describes journal articles:

* [SigmodRecord.xml](http://lambda.uta.edu/cse5335/spring14/SigmodRecord.xml)
* [SigmodRecord.dtd](http://lambda.uta.edu/cse5335/spring14/SigmodRecord.dtd) (the DTD of the document). 

**Note:** the links are NOT broken. Just right click and use "Save Link As" to save the XML and DTD files on your PC.

**Express the following queries:**

1. Print the titles of all articles in volume=13/number=4 whose one of the authors is David Maier.
2. Print the author names off all articles whose title contains the word "database" or "Database".
3. Print the volume/number and the init/end pages of the article titled "Research in Knowledge Base Management Systems.". 

in XPath, DOM, and SAX using Java. 