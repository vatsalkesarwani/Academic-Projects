var marker = new Array();
var geocoder;
var map;
var bound;
/*Initializes the Map*/
function initialize () {
	geocoder = new google.maps.Geocoder();
	var myLatLng = new google.maps.LatLng(32.75, -97.13);
	var mapOptions = {
		center: myLatLng,
		zoom: 16,
		mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('mapCanvas'), mapOptions);
}
/*Retrieves the bounds of the Map*/
function mapBoundFinder() {
	var a = map.getBounds();
	var sw = a.getSouthWest();
	var ne = a.getNorthEast();
	var str = sw + "|" + ne;
	var str1 = str.substring(1,10);
	var str2 = str.substring(18,30);
	var str3 = str.substring(41,50);
	var str4 = str.substring(58,70);
	bound = str1+str2+ "|" + str3 + str4;
	alert(bound);
}
/*Displays the results for the Searched Keyword*/
function sendRequest () {
	mapBoundFinder();
	document.getElementById("searchResult").innerHTML = "";
	var xhr = new XMLHttpRequest();
    var query = encodeURI(document.getElementById("searchText").value);
	xhr.open("GET", "proxy.php?term="+query+"&bounds=" + bound + "&limit=10");
    xhr.setRequestHeader("Accept","application/json");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
		    var json = JSON.parse(this.responseText);
			var results = 10;
		    if(json == null)
				document.getElementById("searchResult").innerHTML = "None";
			else if(json.total < 10)
				results = json.total;
			var node;
			for(var i=0; i<results; i++)
			{
				document.getElementById("searchResult").innerHTML=document.getElementById("searchResult").innerHTML + "<div><div style=float: left; width: 48%;><img alt=Image Not available src=" + json.businesses[i].image_url + "></img></div><div style=float: right; width: 50%;><h4><a href=" + json.businesses[i].url + ">" + (i+1) + " . " + json.businesses[i].name + "</a></h4><img alt=Rating Not available src=" + json.businesses[i].rating_img_url + "></img></div><div style=clear:both; font-size:1px;></div><div><p>" + json.businesses[i].snippet_text + "</p></div></div>";
				var address = "";
				for(j=0; j<json.businesses[i].location.display_address.length; j++) {
					address = address + json.businesses[i].location.display_address[j];
				}
				var k = i+1;
				mapMarkerSetter(address,k);
			}
		//var str = JSON.stringify(json,undefined,2);
		//document.getElementById("sample").innerHTML = "<pre>" + str + "</pre>";
		}
	};
	xhr.send(null);
}
/*Sets the markers for retrieved results on the Map*/
function mapMarkerSetter(address, i) {
	geocoder.geocode({'address':address},function(results,status) {
					if (status == google.maps.GeocoderStatus.OK) {
					map.setCenter(results[0].geometry.location);
					//if(marker != null) {
						//for(var i=0; i<10; i++) {
							//marker[i].setMap(null);
						//}
						//marker=[];
					//}
					marker[i] = new google.maps.Marker({
							icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+i+'|FF0000|000000',
							position: results[0].geometry.location,
							map: map
						});
					} else if(status == google.maps.GeocoderStatus.ZERO_RESULTS) {
						alert("No results");
					}
				});
}