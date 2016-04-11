for $x in doc("auction.xml")/site/people/person
let $item:=doc("auction.xml")/site/regions/europe/item
let $item_ref:=doc("auction.xml")/site/closed_auctions/closed_auction/itemref
let $buyer:=doc("auction.xml")/site/closed_auctions/closed_auction/buyer
where ($buyer/@person)=($x/@id)
and ($item_ref/@item)=($item/@id)
return{$x/name,<items_bought>{$item/name}</items_bought>}