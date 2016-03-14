# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 5 : Implementation of a Message Board using PHP and a Database
MS in CS at University of Texas, Arlington

**Live Demo:** http://omega.uta.edu/~vxk9718/cse5335/project5/board.php

**Credentials:** username: vxk9718 ; password: vatsal

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project5.html Also, alternatively given below.

**Description**

The goal of this project is to learn server-side web programming using PHP and a relational database system (SQLite). More specifically, you will create a message board where registered users can post messages. This project must be done individually. No copying is permitted.

**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

Note that, if you use a Search Engine to find similar programs on the web, we will find these programs too. So don't do it because you will get caught and you will get an F in the course (this is cheating). Don't look for code to use for your project on the web or from other students (current or past). Just do your project alone using the help given in this project description and from your instructor only.

**Platform**

As in the previous projects, you will develop this project on the Omega web server and you will test the project on your PC/laptop using the Mozilla Firefox web browser. Login at omega.uta.edu using SSH and do the following:

cd public_html  
wget http://lambda.uta.edu/cse5335/project5.tgz  
tar xfz project5.tgz  
cp project4/.htaccess project4/.htpasswd project5/  
cd project5

The project5 directory contains the file createDB.sql, which contains the SQL description of the tables: users and posts, that have the following schema:

users ( username, password, fullname, email )  
posts ( id, postedby, datetime, message )

Primary keys: users.username and posts.id  
Foreign key: posts.postedby->users.username

An empty sqlite database has already been created for you, called mydb.sqlite. An example of an sqlite session on omega is:

sqlite3 mydb.sqlite  
select * from users;  
.exit

The project5 directory also contains the file board.php, which needs to be changed as described in the description of the web application. The board.php file uses the PDO extension of PHP to insert a new user and to query the users table using SQLite.

**Documentation**

The following are tutorials on PDO and SQLite. Use them as a reference only.

* [The PHP Data Objects (PDO) extension](http://php.net/manual/en/intro.pdo.php), especially the [PDO class](http://www.php.net/manual/en/class.pdo.php)
* [SQLite](http://sqlite.org/)

**Project Requirements**

Your script board.php must be able to produce 3 kinds of web pages:

1. Page1: a login form that has text windows for username and password, a "Login" button, and a "New users must register here" button
2. Page2: a "Logout" button, the printout of all messages ordered by datetime, and a message textarea with a "Post" button
3. Page3: a form to fill out user information along with a "Submit" button 

When board.php is executed for the first time, it displays Page1:

* If the user enters a wrong username/password and pushes "Login", it should go back to Page1
* If the user enters a correct username/password and pushes "Login", it should go to Page2
* If the user pushes the "New users must register here" button, it should go to Page3 

From Page3, after the user pushes "Submit", it should go to Page1.  
From Page2, if the user pushes "Logout", it should logout and go to Page1.  
From Page2, if the user fills out the textarea and the "Post" button, it will insert the new message in the database and redisplay Page2.  
Note that when there is an error (eg, username already exists), you go back to Page1.

For each posted message, you print:

* The username and the fullname of the person who posted the message
* The date and time when this message was posted
* The message text 

The messages must be printed ordered by date/time (oldest first).

**Hints:** Use [md5](http://us2.php.net/manual/en/function.md5.php) to encode passwords in PHP. Use [uniqid](http://php.net/manual/en/function.uniqid.php) to generate a unique id in PHP. Use the SQLite function [datetime('now')](http://sqlite.org/lang_datefunc.html) to compute the current date and time.