let $doc:=doc("auction.xml")/site/regions/europe/item
for $query in $doc
return{$query/name,$query/description}
