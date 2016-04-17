# Name   : Vatsal Kesarwani
# UTA-ID : 1000869718
# Subject: CSE-5344 Computer Networks

import socket
import sys

def messageHandler(message, ClientSocket):
    requestType = message[0]
    requestPath = message[1]
    requestPath = requestPath[1:]

    #Check if file present in cache
    fileToUse = requestPath
    try:
        file = open(fileToUse, "r")
        data = file.readlines()
        print "File present in cache"
        #Data send by proxy server
        for j in range(0, len(data)):
        	ClientSocket.send(data[j])
        print 'File read from cache'

    except IOError:
        print "File Doesn't Exists In Cache"
        proxyServer = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        hostname = requestPath
        try:
			proxyServer.connect((hostname, 80))
			print 'Socket connected to port 80 of the host'
			fileobj = proxyServer.makefile('r', 0)
			fileobj.write("GET " + "http://" + requestPath + " HTTP/1.0\n\n")
            # Read the response into buffer
			responseBuffer = fileobj.readlines()
            # Created a new file in the cache for the requested file.
            # Also it will send response in the buffer of client socket and the corresponding file in the cache
			tempfile = open("./" + requestPath, "wb")
			for j in range(0, len(responseBuffer)):
				tempfile.write(responseBuffer[j])
				ClientSocket.send(responseBuffer[j])
	except:
			print ''
	ClientSocket.close()

if len(sys.argv) <= 1:
    print 'Usage: "python 1000869718.py portNumber"\n[portNumber : It is the port of the Proxy Server'
    sys.exit(2)

# Create a server socket, bind it to a port and start listening 

# sys.argv[1] is the port number entered by the user
ServerPort = int(sys.argv[1])
# Creating Socket
ServerSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Binding to a port and listening
print "Starting the server"
ServerSocket.bind(('', ServerPort))
ServerSocket.listen(5)

while 1:
    print 'Server ready for accepting the connection...'
    #connection accepted from client
    ClientSocket, addr = ServerSocket.accept() 
    print 'recieved a connection from: ', addr
    while 1 :
	    fileMessage = ClientSocket.recv(1024)
	    message = fileMessage.split()
	    if len(message) < 1:
	    	print 'again connecting to the client'
	    	break
	    else:
	    	if message[1] == '/':
	    		continue
	    	else :
	    		messageHandler(message, ClientSocket)
	    		break