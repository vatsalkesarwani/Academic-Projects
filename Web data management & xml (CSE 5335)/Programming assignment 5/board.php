<!--
	Name  : Vatsal Kesarwani
	UTA Id: 1000869718 
	URL   : http://omega.uta.edu/~vxk9718/cse5335/project5/board.php
-->
<?php
header("HTTP/1.0 404 Not Found");
header("Content-type: text/html; charset=UTF-8");
session_start();
$_SESSION['userName'];
error_reporting(E_ALL);
ini_set('display_errors','On');
?>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>Message Board Login</title>
	</head>
	<body>
		<h1 style="text-align: center;">CSE 5335 Web Data Management and XML</h1>
		<h2 style="text-align: center;">Project 5</h2>
		<h2 style="text-align: center;">Implementation of a Message Board using PHP and a Database</h2>
		<div style="text-align: center;">
			<form  method="POST" style="text-align: center;">
				<label><b>Registered User</b></label><br/>
                <label><b>Username: </b><input type="text" name="username"/></label><br/>
                <label><b>Password: </b><input type="password" name="password"/></label><br/>
				<input type="submit" value="Login" name="login"/><br/><br/>
				<label><b>New User</b></label><br/>
				<input type="submit" value="Register" name="register"/><br/>
			</form>
		</div>
<?php
if(isset($_POST["login"])) {
	try {
		$dbname = dirname($_SERVER["SCRIPT_FILENAME"])."/mydb.sqlite";
		$dbh = new PDO("sqlite:$dbname");
		$dbh->beginTransaction();
		$stmt = $dbh->prepare("select * from users where username='".$_POST["username"]."' and password='".md5($_POST["password"])."'");
		$stmt->execute();
		$i = 0;
		foreach($stmt as $row) {
			$i = $i + 1;
		}
		if($i===1) {
			$_SESSION['userName'] = $_POST["username"];
			header("Location:post.php");
		}
	} catch (PDOException $e) {
		print "Error!: " . $e->getMessage() . "<br/>";
		die();
	}
}
if(isset($_POST['register'])) {
	header("Location:register.php");
}
?>
	</body>
</html>
