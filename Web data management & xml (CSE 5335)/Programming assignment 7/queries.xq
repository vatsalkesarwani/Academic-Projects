{
let $statement1 := "Solution 1: Number of items listed on all continents = "
return $statement1
},{
for $itemCount in count(doc("auction.xml")//regions/*/item)
let $solution1 := $itemCount
return $solution1
},{
let $statement2 := concat('&#10;',"Solution 2: List of product names with description registered in europe are: ")
return $statement2
},{
for $item at $i in doc("auction.xml")//regions/europe/item
let $solution2 := concat('&#10;','Name ',$i,': ',$item/name,'&#10;','Description: ',data($item/description))
return $solution2
},{
let $statement3 := concat('&#10;',"Solution 3: List of people names with number of items bought are: ")
return $statement3
},{
for $auctions in doc("auction.xml")//closed_auctions/closed_auction
let $personName := $auctions/buyer/@person
group by $personName
order by $personName
let $solution4 := concat('&#10;','Name: ',doc("auction.xml")//people/person[@id = $auctions/buyer/@person]/name,' No.of items: ',sum($auctions/quantity))
return $solution4
},{
let $statement4 := concat('&#10;',"Solution 4: List of persons according to their interests are: ")
return $statement4
},{
for $x in doc("auction.xml")//people/person
let $category := distinct-values($x/profile/interest/@category)
let $solution4 := concat('&#10;','Category: ',$category,' Name: ',$x/name)
where $category ne ''
group by $category
return $solution4
},{
let $statement5 := concat('&#10;',"Solution 5: Size of each group of persons categorised by their interest categories are: ")
return $statement5
},{
for $category in doc("auction.xml")//categories/category
let $people := doc("auction.xml")//people/person
let $peopleName := $people/profile/interest[@category = $category/@id]/../../name
let $solution5 := concat('&#10;','Category: ',$category/name,' No. of persons: ',count($peopleName))
return $solution5
},{
let $statement6 := concat('&#10;',"Solution 6: List of persons and items they bought in Europe are: ")
return $statement6
},{
let $items := doc("auction.xml")//regions/europe/item
let $y := doc("auction.xml")//closed_auctions/closed_auction
for $x in doc("auction.xml")//people/person
let $abc := $items[@id = $y/buyer[@person=$x/@id]/../itemref/@item]/name
let $solution6 := concat('&#10;','Person Name: ',$x/name,' No. of items: ',$abc)
where $abc ne ''
return $solution6
},{
let $statement7 := concat('&#10;',"Solution 7: Alphabetically ordered list of items along with their location is: ")
return $statement7
},{
for $itemDetails in doc("auction.xml")//regions/*/item
let $solution7 := concat('&#10;','Item Name: ',$itemDetails/name,' Location: ',$itemDetails/location)
order by $itemDetails/name
return $solution7
},{
let $statement8 := concat('&#10;',"Solution 8: The reserve prices of the open auctions where person3 bid before person6 are: ")
return $statement8
},{
for $auctions in doc("auction.xml")//open_auctions/open_auction
let $solution8 := concat('&#10;','Open Auction Id: ',$auctions/@id,' Reserve Price: ',$auctions/reserve)
where $auctions/bidder/personref[(@person='person3') < (@person='person6')]
return $solution8
}