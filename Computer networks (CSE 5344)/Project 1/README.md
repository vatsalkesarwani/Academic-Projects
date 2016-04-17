# Academic-Projects
### Computer Networks (CSE 5344)
#### Project 1 : Multithreaded Web Proxy Server
MS in CS at University of Texas, Arlington

**Project's Requirements:** The pdf file ["Project requirements.pdf"](/Computer networks (CSE 5344)/Project 1/Project requirements.pdf) describes all the requirements for the project.

**Project Specification:** In this project, multithreaded web proxy server performs the following functionalities:

* Receiving HTTP requests (GET and POST method) from a browser and forwarding them to the origin server.
* Sending corresponding HTTP responses received from the origin server to the client.
* Handle errors when a client requests an object which is not available.
* Caching web pages when the client makes a particular request for the first time and send the cached web page to the client when a cache hit occurs.

**Steps on how to run the program:**

1. Use Python 2.6 version
2. Open source code (*.py) python file (sample.py) in TextWrangler
3. Open the command terminal and reach the destination folder where program is saved
4. Type command 'python <file name> <port number>Õ for ex. python sample.py 5000
5. Proxy Server is ready to accept your request
6. Open browser and enter the url in following format http://localhost:<port number>/<website to open> for ex. http://localhost:5000/srmuniv.ac.in
7. The requested website will open in the browser.
8. The terminal will display the message whether the file is read from the cache or connected to origin server.