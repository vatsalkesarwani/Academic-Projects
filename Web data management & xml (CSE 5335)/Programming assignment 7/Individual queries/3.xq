let $persons:= doc("auction.xml")/site/people
for $persons1 in $persons//person
let $count := $persons1/watches	
return
<person_name>
{
	$persons1/name, 
	 <item_count>{count($count/watch)}</item_count>
}
</person_name>
