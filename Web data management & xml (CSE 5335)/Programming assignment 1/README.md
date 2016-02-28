# Academic-Projects
### Web Data Management & XML (CSE 5335)
#### Programming Assignment 1 : A JavaScript Game
MS in CS at University of Texas, Arlington

**Live Demo:** http://omega.uta.edu/~vxk9718/cse5335/project1/pong.html

**Programming Assignment's Requirements:** http://lambda.uta.edu/cse5335/spring14/project1.html **Also, alternatively given below.**

**Description**

The goal of this project is to learn JavaScript and DOM by implementing a simple computer game, reminiscent to the Pong arcade video game from the 70s. This project must be done individually. No copying is permitted.  
Note: We will use a system for detecting software plagiarism, called Moss, which is an automatic system for determining the similarity of programs. That is, your program will be compared with the programs of the other students in class as well as with the programs submitted in previous years.

**Platform**

You will do this project on your own PC/laptop. You have to use the Mozilla Firefox web browser and you can use the fire-bug plug-in for debugging your JavaScript code. To run JavaScript expressions, such as print the value of a variable, activate Fire-bug and on its Console menu select "Show Command Editor", then type an expression such as 1+2 and hit Run.

**Documentation**

The following web pages contain various tutorials. Use them as a reference only. The class slides contain enough information on JavaScript and DOM.

* JavaScript Tutorial -> http://www.w3schools.com/js/default.asp
* JavaScript -> http://www.cs.rochester.edu/courses/210/spring2011/lectures/009/
* JavaScript DOM -> http://www.cs.rochester.edu/courses/210/spring2011/lectures/010/

**Project Description**

You need to write a JavaScript file pong.js, used in the file pong.html, that implements the following actions:

* initialize: initialize the game
* startGame: starts the game (when you click the mouse)
* setSpeed: sets the speed to 0 (slow), 1 (medium), 2 (fast)
* resetCounter: resets the score to zero
* movePaddle: moves the paddle up and down, by following the mouse

Please watch the video pong.mp4 for a demo of how your game should look like. Link -> http://lambda.uta.edu/cse5335/spring14/pong.mp4

**Description:**

The pong court is 1100x600px, the pong ball is 50x50px, and the paddle is 102x14px. When you click left on the court, the ball will start from a random place at the left border of the court at a random angle between -π/4 and π/4. The paddle can move up and down on the right border by just moving the mouse (without clicking the mouse). The ball bounces at the left, top, and bottom borders of the court. If the ball crosses the right border, you loose a point and the game is suspended (you would need to click on the court again to resume). So the goal of this game is to move the paddle to protect the right border by hitting the ball.

**Hints:**

* The position of any element is dictated by the three style properties: position, left, and top. If an element is nested inside another and its position is "absolute", the top and left properties are relative to the enclosing element.  
  <p id="x" style="position: absolute; left: 50px; top: 100px;"> ... </p>  
  To move this element, just change the left/top attributes using code:  
  document.getElementById("x").style.top = "10px";

* Note that the values that you set the left/top attributes must have units (e.g., "10px"). It will not work if you set them to numbers.
* You can get the X and Y coordinates of the mouse using the pageX and pageY attributes of an event (e.g., from the event that is passed on the onmousemove handler).
* To animate an element, it must be moved by small amounts, many times, in rapid succession. For example, you can use setTimeout("fun()", n) that calls fun() after a delay of n milliseconds (you have to put it in a loop or use recurion).
* It will be easier to develop your code by first ignoring the paddle and making all the court borders solid, so the ball will bounce on every border. After you make this work, you can change your code so that the ball that tries to cross the right border bounces if it hits the paddle. You need to define a time period (the "tick") dt to calculate the new x/y coordinates from the current. The speed coordinates vx/vy are determined when the ball is kick-started (from the kick angle). The new x is x+vx*dt, but if the new value is beyond the right border, then the ball must be bounced by seting vx = -vx and x = 2*width-x, assuming that the court x-coordinates are from 0 to width. You do something similar for the left, top, and, bottom borders.

Note: You should use plain JavaScript. You should not use any JavaScript library, such as JQuery. You should not use the JavaScript canvas object.