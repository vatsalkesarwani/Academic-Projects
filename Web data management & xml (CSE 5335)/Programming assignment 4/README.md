# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 4 : PHP Scripting
MS in CS at University of Texas, Arlington

**Live Demo:** http://omega.uta.edu/~vxk9718/cse5335/project4/buy.php

**Credentials:** username: vxk9718 ; password: vatsal

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project4.html Also, alternatively given below.

**Description**

The goal of this project is to learn server-side web programming using PHP. More specifically, you will develop an e-commerce web application where customers can buy products listed by the eBay Commerce Network API for Shopping. This project must be done individually. No copying is permitted.

**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

Note that, if you use a Search Engine to find similar programs on the web, we will find these programs too. So don't do it because you will get caught and you will get an F in the course (this is cheating). Don't look for code to use for your project on the web or from other students (current or past). Just do your project alone using the help given in this project description and from your instructor only.

**Platform**

As in Projects #2 and #3, you will develop this project on the Omega web server and you will test the project on your PC/laptop using the Mozilla Firefox web browser. Login on omega.uta.edu using SSH and do the following:

cd public_html  
wget http://lambda.uta.edu/cse5335/project4.tgz  
tar xfz project4.tgz  
cp project3/.htaccess project3/.htpasswd project4/  
cd project4

The project4 directory contains the file buy.php, which needs to be changed as described in the description of the web application.

**Documentation**

The following are tutorials on PHP. Use them as a reference only.

* [PHP Tutorial](http://www.w3schools.com/php/default.asp)
* [PHP](http://www.cs.rochester.edu/courses/210/spring2011/lectures/005/)
* [PHP.net](http://php.net/manual/en/), especially the [SimpleXML](http://php.net/manual/en/simplexml.examples-basic.php) interface 

You will use PHP sessions and the PHP SimpleXML interface. Using SimpleXML, to access all children of $x with tag A, use $x->A. To access the value of an attribute A of an element $x, use $x['A']. To store a SimpleXML value $x in the $_SESSION, you need to coerce it to a string first using (string)$x.

$xmlstr = file_get_contents('someURL.com');  
$xml = new SimpleXMLElement($xmlstr);  
foreach ($xml->BookList->BookData as $b)  
    foreach ($b->Prices->Price as $p)  
        if ($p['store_id'] == "amazon")  
            print $b['isbn'] . ' ' . $b->Title[0] . ' ' . $p['price'] . "\n";

**Project Requirements**

You will develop a trivial web application that allows customers to buy products. You need to reverse-engineer my PHP script:

https://lambda.uta.edu/cgi-bin/php/buy.php

by filling out its forms and by looking at the HTML source that is generated by this script. Your task is to modify your own buy.php script in your project4 directory on Omega to make it behave in the same way as my buy.php, although you should make your script generate better HTML code to look nicer.

You will use the shopping.com API for shopping. The [eBay Commerce Network API](http://developer.ebaycommercenetwork.com/docs/Getting_Started) ("ECN API") is a flexible way to access and recreate practically everything you see on Shopping.com. First, you need to get an API key. You can use the [default key](https://lambda.uta.edu/cgi-bin/php/browse.php?class=cse5335&file=passwords.txt). We will only use the "Search by keyword" method from the [eBay Commerce Network Publisher API Use Cases](http://developer.ebaycommercenetwork.com/docs/API_Use_Cases#1).

The result of a keyword search typically contains 5 products that best match the keyword query. Each product contains a link productOffersURL to the shopping.com web page that gives a detailed description of the product and a list of best offers from various sellers. So each product has a range minPrice - maxPrice of the prices offered by these sellers. We will ignore the list of offers and we will assume that when we buy this product we pay the minPrice.

You need to use a PHP session to store the shopping basket (the list of chosen items throughout the session) as well as the results of the last search. For each chosen item, you store the Id, the name, the minPrice, the first image, and the productOffersURL (the link to the shopping.com web page that lists the best offers for this item). Your PHP script must be able to handle the following query strings:

* To search for all items that match the search keywords "digital camera":

  buy.php?search=digital+camera

  This should call the web service request http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=xxx&trackingId=yyy&keyword=digital+camera, where xxx and yyy are your access apiKey and your trackingId. The web service results will arrive in XML. To see how the XML data look like, cut and paste this URL on your browser and look at the reply.

* To put a listed item with id=138681275 in the shopping basket (if it does not exist):

  buy.php?buy=138681275

  Your script should look at the results of the last search stored in $_SESSION to find the item under this Id and should copy it into the shopping basket.

* To remove a listed item with Id=138681275 from the shopping basket (if exists):

  buy.php?delete=138681275

* To clear the shopping basket:

  buy.php?clear=1

**Hints:** you can use the PHP function [print_r](http://us3.php.net/manual/en/function.print-r.php) for debugging. It prints human-readable information about a variable. 