var initialTop=0;
var initialAngle=0;
var initialSpeed;
var currentScore;
var xDirection;
var yDirection;
var curLeft;
var curTop;
var tanTheta;
var counter;
/*Initializes the Game*/
function initialize()
{
	//Randomly decides initial position of ball
	initialTop=Math.floor((Math.random()*550)-50);
	//Randomly decides initial angle of ball
	initialAngle=Math.floor((Math.random()*90)-45);
	//Sets the initial speed of ball
	initialSpeed=1;
	//Sets the initial score of game to zero
	currentScore=0;
	//Sets the current top position of ball to zero
	curTop=0;
	//Sets the direction to true(true means movement of ball from left to right and vice-versa)
	xDirection=true;
	if(initialAngle>0)
		//Sets the direction to true(true means movement of ball from down to top)
		yDirection=true;
	else
		//Sets the direction to false(false means movement of ball from top to down)
		yDirection=false;
}
/*Starts the Game*/
function startGame(event)
{
	//Sets the top position of ball
	document.getElementById("gameBall").style.top=initialTop+"px";
	//Sets the left position of ball
	document.getElementById("gameBall").style.left="0px";
	//Sets the current left position of ball
	curLeft=0;
	if(curTop==0)
	//Sets the current top position of ball
	curTop=initialTop;
	//Computes tan for the initial angle of ball
	tanTheta=Math.tan(initialAngle*Math.PI/180);
	//Moves the ball in iterations
	counter=setInterval("ballMove()", 5);
}
/*Sets the Speed for the Ball*/
function setSpeed(varSpeed)
{
	//Sets different speed values depending on the radio button clicked
	initialSpeed=(varSpeed*1)+1;
}
/*Resets the Scoreboard to Zero*/
function resetCounter()
{
	currentScore=0;
	//Sets the score back to zero
	document.getElementById("scoreBoard").innerHTML=0;
}
/*Moves the paddle UpDown*/
function movePaddle(event)
{
	//Gets the Y-coordinate of mouse
	var paddleTop=event.clientY;
	if(paddleTop>=-50 && paddleTop<=500)
	//Sets the top position of paddle from y-coordinate of mouse
	document.getElementById("gamePaddle").style.top=paddleTop+'px';
}
/*Increases the Score on the ScoreBoard*/
function increaseScore()
{
	//Increases the current score by one
	currentScore=currentScore+1;
	//Updates the current score on web-page
	document.getElementById("scoreBoard").innerHTML=currentScore;
}
//Moves the ball 
function ballMove()
{
	if(xDirection==true)
	{
		if((curLeft<998 && curLeft>=0) || (curLeft<1050 && curLeft>1005))
		{
			curLeft=curLeft+initialSpeed;
		}
		else if(curLeft<=1005 && curLeft>=998)
		{
			var paddleTop = parseInt(document.getElementById("gamePaddle").style.top);
			if(curTop>=(paddleTop-90) && curTop<=(paddleTop+42))
			{
				xDirection=false;
				curLeft=curLeft-initialSpeed;
			}
			else
			{
				curLeft=curLeft+initialSpeed;
			}
		}
		else if(curLeft>=1050)
		{
			curLeft=0;
			increaseScore();
			clearInterval(counter);
		}
		else
		{
			xDirection=false;
			curLeft=curLeft-initialSpeed;
		}
	}
	else 
	{
		if(curLeft<=1000 && curLeft>0)
		{
			curLeft=curLeft-initialSpeed;
		}
		else
		{
			xDirection=true;
			curLeft=curLeft+initialSpeed;
		}
	}
	if(yDirection==true)
	{
		if(curTop<=500 && curTop>-50)
		{
			curTop=curTop+(initialSpeed*tanTheta);
		}
		else
		{
			yDirection=false;
			curTop=curTop-(initialSpeed*tanTheta);
		}
	}
	else 
	{
		if(curTop<500 && curTop>=-50)
		{
			curTop=curTop-(initialSpeed*tanTheta);
		}
		else
		{
			yDirection=true;
			curTop=curTop+(initialSpeed*tanTheta);
		}
	}
	document.getElementById("gameBall").style.left=curLeft+"px";
	document.getElementById("gameBall").style.top=curTop+"px";
}