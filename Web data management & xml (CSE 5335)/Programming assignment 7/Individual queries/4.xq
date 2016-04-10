let $categories:= doc("auction.xml")/site//categories
for $interest_category in $categories//category
return ($interest_category/name,
	let $people:= doc("auction.xml")/site/people
	for $person in $people/person
	where $person/profile/interest[@category=$interest_category/@id]
	return ($person/name))