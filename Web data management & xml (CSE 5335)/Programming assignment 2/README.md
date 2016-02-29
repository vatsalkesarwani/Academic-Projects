# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 2 : A Movie Web Application
MS in CS at University of Texas, Arlington

**Live Demo:** http://omega.uta.edu/~vxk9718/cse5335/project2/movies.html

**Credentials:** username: vxk9718 ; password: vatsal

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project2.html Also, alternatively given below.

**Description**

The goal of this project is to learn client-side web programming using JavaScript and AJAX. More specifically, you will create a Web application that displays information about movies. This project must be done individually. No copying is permitted.  
**Note:** We will use a system for detecting software plagiarism, called [Moss](http://theory.stanford.edu/~aiken/moss/), which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

**Platform**

You will develop this project on the Omega web server. Look at the directions at [omega.uta.edu student web page](http://www.uta.edu/oit/cs/web/OmegaUtaEdu-webPage.php). Please call UTA OIT if you have problems with your omega account. You will test the project on your PC/laptop using the Mozilla Firefox web browser. You can use the [fire-bug](https://addons.mozilla.org/en-US/firefox/addon/firebug/) plug-in for debugging your JavaScript code.

**Setting up your project on Omega**

* Login on omega and look at your path using the unix command pwd. For example, if your username is xyz1234, you will see:  
  */home/x/xy/xyz1234*
* In the following, replace the path */home/x/xy/xyz1234* with your own path. Then, you do the following:  
  cd public_html  
  wget http://lambda.uta.edu/cse5335/spring14/project2.tgz  
  tar xfz project2.tgz  
  cd project2  
  ls -al
* Then edit the file *.htaccess* (note: it starts with a dot) inside the project2 directory using your favorite editor (vi, pico, emacs, etc), and change the line:  
  *AuthUserFile /home/f/fe/fegaras/public_html/project2/.htpasswd*  
  to point to your own path. Then do:  
  *htpasswd .htpasswd xyz1234*  
  (use your username instead of xyz1234). It will ask you for a password twice. This is the password you use when you run your web application on a browser. It already contains a password for the GTA; so the GTA can run the script of any student but you can only run your own script.

The project2 directory contains 3 files: proxy.php, movies.html, and movies.js. The proxy script proxy.php is used to avoid the cross-domain restriction when using Ajax. All the web service requests to TMDb should go through this proxy. See the example in movies.js. Your project is to edit movies.html and movies.js as described in the description of the web application.

**Getting an access key from TMDb**

You are going to use the Web Service REST API of the movie DB [TMDb](https://www.themoviedb.org/documentation/api). You first need to get an API access key from [Sign up a TMDb account](https://www.themoviedb.org/account/signup). The access key will allow you to send web service requests to TMDb (maximum 3 requests per second).

After you get the API key, you put it in proxy.php and you test your setup on your web browser by using the URL address:

*http://omega.uta.edu/~xyz1234/project2/movies.html*

(use your username instead of xyz1234). and by typing the title of your favorite movie. This will display a list of movies that match this title in JSON form.

**Documentation**

The following web pages contain various tutorials. Use them as a reference only. The class slides contain enough information on JavaScript and AJAX.

* [The Movie Database API](http://docs.themoviedb.apiary.io/)
* [AJAX Tutorial](http://www.w3schools.com/ajax/default.asp)
* [Ajax](http://www.cs.rochester.edu/courses/210/spring2011/lectures/012/)

**Description of the Web Application**

Your project is to develop a web application to get information about movies, their cast, their posters, etc. This application should be developed using plain JavaScript and Ajax. You should not use any JavaScript library, such as JQuery. Note that everything should be done asynchronously and your web page should never be redrawn/refreshed completely. This means that the buttons or any other input element in your HTML forms must have JavaScript actions, and should not be regular HTTP requests.

Your application should have a text section where one can type a movie title (eg, The Matrix), one "Display Info" button to search, one section to display the search results, and one section to display information about a movie. The search results is an itemized clickable list of movie titles along with their years they were released. When you click on one of these movie titles, you display information about the movie: the poster of the movie as an image, the movie title, its genres (separated by comma), the movie overview (summary), and the names of the top five cast members (ie, actors who play in the movie).

You need to use the following TMDb HTTP methods listed in [The Movie Database API](http://docs.themoviedb.apiary.io/):

* */3/search/movie:* Search for movies by title.
* */3/movie/{id}:* Get the basic movie information for a specific movie id.
* */3/movie/{id}/credits:* Get the cast and crew information for a specific movie id.

You need to call the TMDb web service through the proxy.php. For example, to get information about the movie "The Matrix" (which has id 603), you use the HTTP call proxy.php?method=/3/movie/603 (it doesn't need the API key -- it's already in the proxy). To search for the movie "matrix", you call proxy.php?method=/3/search/movie&query=matrix.

Note that there is a lot of information returned by these web services. You need to use very few parts of this information only. 