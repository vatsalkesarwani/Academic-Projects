# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 9 : Data Analysis using Map-Reduce
MS in CS at University of Texas, Arlington

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project9.html Also, alternatively given below.

**Description**

The purpose of this project is to develop a simple Map-Reduce program on Hadoop to analyze data. This project must be done individually. No copying is permitted.

**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

Note that, if you use a Search Engine to find similar programs on the web, we will find these programs too. So don't do it because you will get caught and you will get an F in the course (this is cheating). Don't look for code to use for your project on the web or from other students (current or past). Don't try to hire a freelancer to do your coding for you. Just do your project alone using the help given in this project description and from your instructor only.

**Platform**

You will develop this project on Omega or your own PC/laptop. If you want use your own PC (Mac or Linux only), you need to install [Apache Hadoop](http://hadoop.apache.org/docs/r1.2.1/single_node_setup.html). Note: Hadoop does not work on Windows. To use Hadoop on Omega, download Apache Hadoop 1.2.1. and do the following to extract:

tar -xvf hadoop-1.2.1.tar.gz 

Edit hadoop-env.sh file in conf directory to set JAVA_HOME: add the following line in the file to set it:

export JAVA_HOME=/opt/jdk1.6.0_20

Hadoop can be run from your Hadoop directory with the following command in STANDALONE MODE.

bin/hadoop jar YourJarFile.jar YourMainClass YourProgramArguments1 YourProgramArguments2

Update 04/17/2014: You don't need to download Hadoop on omega. It's now available at:

/home/u/ux/uxg4406/public_html/project9/hadoop-1.2.1/

Don't try to change to a parent directory. It will not work. Only this directory is read-accessible to students. just cut-and-paste and execute this command on omega to see the files:

ls /home/u/ux/uxg4406/public_html/project9/hadoop-1.2.1

**Project Requirements**

You will use the [orders.tbl](http://omega.uta.edu/~uxg4406/project9/orders.tbl) with the following schema:

ORDERKEY | CUSTKEY | ORDERSTATUS | TOTALPRICE | ORDERDATE | ORDER-PRIORITY | CLERK | SHIP-PRIORITY | COMMENT
--- | --- | --- | --- | --- | --- | --- | --- | ---

You need to implement the following SQL query in the Map-Reduce Framework.

SELECT CustKey, SUM(TotalPrice) FROM orders GROUP BY CustKey.

* Write a Map Reduce Java program that takes order.tbl as input and, for each different CustKey, it prints the CustKey and the total price. 