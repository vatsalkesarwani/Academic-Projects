<?php
session_start();
?>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <title>Message Board</title>
    </head>
    <body>
        <h1 style="text-align: center;">CSE 5335 Web Data Management and XML</h1>
        <h2 style="text-align: center;">Project 5</h2>
        <h2 style="text-align: center;">Implementation of a Message Board using PHP and a Database</h2>
        <div style="text-align: center;">
            <table align="center">
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th>Full Name</th>
					<th>Date & Time</th>
					<th>Message</th>
				</tr>
<?php
if(isset($_POST["post"])) {
	try {
		//date_default_timezone_set('UTC');
        $dbname = dirname($_SERVER["SCRIPT_FILENAME"])."/mydb.sqlite";
        $dbh = new PDO("sqlite:$dbname");
        $dbh->beginTransaction();
		$uid = uniqid();
		$stmt2 = $dbh->prepare("select datetime('now')");
        $stmt2->execute()
		      or die(print_r($dbh->errorInfo(), true));
	    foreach($stmt2 as $row2) {
			$postTime = $row2[0];
		}
		$stmt = $dbh->prepare("insert into posts values('".$uid."','" .$_SESSION["userName"]. "','".$postTime."','".$_POST["message"]."')");
		$stmt->execute()
              or die(print_r($dbh->errorInfo(), true));
        $dbh->commit();
        $stmt1 = $dbh->prepare('select * from posts');
        $stmt1->execute()
		      or die(print_r($dbh->errorInfo(), true));
        foreach($stmt1 as $row1) {
			$stmt3 = $dbh->prepare("select fullname from users where username='".$row1['postedby']."'");
			$stmt3->execute()
				or die(print_r($dbh->errorInfo(), true));
			foreach($stmt3 as $row3) {
				$fullName = $row3[0];
			}
			print "<tr>";
			print "<td>".$row1['id']."</td>";
			print "<td>".$row1['postedby']."</td>";
			print "<td>".$fullName."</td>";
			print "<td>".$row1['datetime']."</td>";
			print "<td>".$row1['message']."</td>";
			print "</tr>";
        }
    } catch (PDOException $e) {
        print "Error!: " . $e->getMessage() . "<br/>";
        die();
    }
}
?>
			</table>
			<form  method="POST" style="text-align: center;">
                <input type="text" name="message"/><br/>
				<input type="submit" value="Post" name="post"/><br/>
                <input type="submit" value="Logout" name="logout"/><br/>
            </form>
        </div>
<?php
if(isset($_POST["logout"])) {
	session_destroy();
	header("Location:board.php");
}
?>
	</body>
</html>
