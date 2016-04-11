for $x in doc("auction.xml")/site/regions//item
order by $x/name
return{$x/name,$x/location}