<!--
	Name  : Vatsal Kesarwani
	UTA Id: 1000869718
	URL   : http://omega.uta.edu/~vxk9718/cse5335/project4/buy.php
-->
<?php
header("HTTP/1.0 404 Not Found");
header("Content-type: text/html; charset=UTF-8");
session_start();
$_SESSION['totalCost'];
//error_reporting(E_ALL);
//ini_set('display_errors','On');
?>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>Buy Products</title>
	</head>
	<body>
		<h1 style="text-align: center;">CSE 5335 Web Data Management and XML</h1>
		<h2 style="text-align: center;">Project 4</h2>
		<h2 style="text-align: center;">PHP Scripting: Buy Products</h2>
		<div>
			<table border='1' style="width: 100%;">
				<tr>
					<th>Shopping Cart:</th>
				</tr>
				<tr>
					<th style="width: 15%">Product Image</th>
					<th style="width: 65%">Product Name</th>
					<th style="width: 10%">Price</th>
					<th style="width: 10%"></th>
				</tr>
<?php
if(isset($_GET["buy"])) {
	foreach ($_SESSION['searchResult'] as $oneItemID=>$oneItem) {
		if($_GET["buy"] == $oneItemID) {
			$_SESSION['cart'][(string)$oneItemID] = array($oneItem[0], $oneItem[1], $oneItem[2], $oneItem[3]);
			if(isset($_SESSION['totalCost'])) {
				$_SESSION['totalCost'] = $_SESSION['totalCost'] + $oneItem[3];
			} else {
				$_SESSION['totalCost'] = 0;
			}
		}
	}
}
if(isset($_GET["delete"])) {
	foreach ($_SESSION['cart'] as $oneProductID=>$oneProduct) {
		if($_GET["delete"] == $oneProductID) {
			if(isset($_SESSION['totalCost'])) {
					$_SESSION['totalCost'] = $_SESSION['totalCost'] - $oneProduct[3];
			} else {
					$_SESSION['totalCost'] = 0;
			}
			unset($_SESSION['cart'][(string)$oneProductID]);
		}
	}
}
if(isset($_GET["clear"])) {
	foreach ($_SESSION['searchResult'] as $oneItemID=>$oneItem) {
		unset($_SESSION['searchResult'][(string)$oneItemID]);
	}
	foreach ($_SESSION['cart'] as $oneProductID=>$oneProduct) {
		unset($_SESSION['cart'][(string)$oneProductID]);
	}
	$_SESSION['totalCost'] = 0;
}
if(isset($_SESSION['cart'])) {
	foreach ($_SESSION['cart'] as $oneProductID=>$oneProduct) {
        echo "<tr>";
        echo "<td><a href=".$oneProduct[0]."><img src=".$oneProduct[1]."></img></a></td>";
        echo "<td>".$oneProduct[2]."</td>";
		echo "<td><label>$</label>".$oneProduct[3]."</td>";
        echo "<td><a href=buy.php?delete=".(string)$oneProductID.">delete</a></td>";
        echo "</tr>";
    }
}
?>
				<tr>
					<td>
					</td>
					<td><b>Total Amount:</b></td>
					<td>
						<label>$</label>
<?php
if(isset($_SESSION['totalCost']))
	echo $_SESSION['totalCost'];
else
	echo "0";
?>
					</td>
					<td>
						<form action="buy.php" method="GET" style="width: 100%; height:100%;">
							<input type="hidden" name="clear" value="1">
							<input type="submit" value="Empty Cart" style="width: 100%;"/>
						</form>
					</td>
				</tr>
			</table>
		</div>
		<form action="buy.php" method="GET" style="text-align: center;">
			<fieldset>
				<legend>Find products:</legend>
				<label><b>Search for items: </b><input type="text" name="searchProduct"/></label>
				<input type="submit" value="Search"/>
			</fieldset>
		</form>
		<div>
			<table border='1' style="width: 100%;">
				<tr>
					<th style="width: 15%">Product Image</th>
					<th style="width: 75%">Product Name</th>
					<th style="width: 10%">Price</th>
				</tr>
<?php
header("Content-Type: text/html;charset=UTF-8");
if(isset($_GET["searchProduct"])) {
	$searchText = str_ireplace(" ", "+", $_GET["searchProduct"]);
	$xmlstr = file_get_contents('http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&trackingId=7000610&keyword='.$searchText);
	$xml = simplexml_load_string($xmlstr);
	foreach ($xml->categories->category->items->product as $oneProduct) {
		$_SESSION['searchResult'][(string)$oneProduct['id']] = array((string)$oneProduct->productOffersURL, (string)$oneProduct->images->image[0]->sourceURL, (string)$oneProduct->name, (float)$oneProduct->minPrice);
		echo "<tr>";
		echo "<td><a href=buy.php?buy=".$oneProduct['id']."><img src=".$oneProduct->images->image[0]->sourceURL."></img></a></td>";
		echo "<td>".$oneProduct->name."</td>";
		echo "<td>".$oneProduct->minPrice."</td>";
		echo "</tr>";
	}
}
?>
			</table>
		</div>
		<br/>
	</body>
</html>