let $category:=doc("auction.xml")/site/categories/category
for $comparison in doc("auction.xml")/site/categories/category
let $person:=doc("auction.xml")/site/people/person
where $comparison/@id=$person/profile/interest/@category
return {$category/name,(<count_of_persons>{count($person)}</count_of_persons>)}


