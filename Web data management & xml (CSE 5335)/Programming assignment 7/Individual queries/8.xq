for    $x in doc("auction.xml")/site/open_auctions/open_auction
where  doc("auction.xml")/site/open_auctions/open_auction/bidder/personref[(@person="person3") < (@person="person6")]
return  {($x/reserve)}
