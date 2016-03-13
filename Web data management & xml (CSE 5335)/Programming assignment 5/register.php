<?php
header('Access-Control-Allow-Origin: http://omega.uta.edu/~vxk9718/cse5335/project5/board.php');
?>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>Message Board Registration</title>
	</head>
	<body>
		<h1 style="text-align: center;">CSE 5335 Web Data Management and XML</h1>
		<h2 style="text-align: center;">Project 5</h2>
		<h2 style="text-align: center;">Implementation of a Message Board using PHP and a Database</h2>
		<div style="text-align: center;">
			<form  method="POST" style="text-align: center;">
				<label><b>New User</b></label><br/>
                <label><b> Full Name: </b><input type="text" name="fullname"/></label><br/>
                <label><b>  Username: </b><input type="text" name="username"/></label><br/>
                <label><b>   Password: </b><input type="password" name="password"/></label><br/>
                <label><b>    Email Id: </b><input type="text" name="email"/></label><br/>
				<input type="submit" value="Submit" name="Submit"/><br/><br/>
			</form>
		</div>
<?php
if(isset($_POST["Submit"])) {
	try {
		$dbname = dirname($_SERVER["SCRIPT_FILENAME"])."/mydb.sqlite";
		$dbh = new PDO("sqlite:$dbname");
		$dbh->beginTransaction();
		$stmt = $dbh->prepare("insert into users values('".$_POST["username"]."','" . md5($_POST["password"]) . "','".$_POST["fullname"]."','".$_POST["email"]."')");
		$stmt->execute()
		//$dbh->exec('insert into users values($_GET["username"],"' . md5($_GET["email"]) . '",$_GET["fullname"],$_GET["email"])')
			or die(print_r($dbh->errorInfo(), true));
		$dbh->commit();
		header("Location:board.php");
	} catch (PDOException $e) {
		print "Error!: " . $e->getMessage() . "<br/>";
		header("Location:board.php");
		die();
	}
}
?>
	</body>
</html>
