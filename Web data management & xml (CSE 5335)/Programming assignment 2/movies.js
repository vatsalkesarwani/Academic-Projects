/* Initializes the Search Results & Movie Details display area*/
function initialize () {
document.getElementById("resultUList").innerHTML = "";
document.getElementById("movieDetails").innerHTML = "";
}
/*Displays the results for the Searched Keyword*/
function sendRequest () {
	var xhr = new XMLHttpRequest();
	var query = encodeURI(document.getElementById("form-input").value);
	xhr.open("GET", "proxy.php?method=/3/search/movie&query=" + query);
	xhr.setRequestHeader("Accept","application/json");
	xhr.onreadystatechange = function () {
		if (this.readyState == 4) {
			var json = JSON.parse(this.responseText);
			document.getElementById("resultHeading").style.display = "block";
			document.getElementById("resultUList").innerHTML = "";
			if(json.total_results == 0)
				document.getElementById("resultUList").innerHTML = "None";
			for(i=0; i<json.total_results; i++)
			{
				if(json.results[i].release_date != "")
					document.getElementById("resultUList").innerHTML = document.getElementById("resultUList").innerHTML + "<li onclick=displayDetails(" + json.results[i].id + ");>" + json.results[i].title + " (" + json.results[i].release_date.substring(0,4) + ")</li>";
				else
					document.getElementById("resultUList").innerHTML = document.getElementById("resultUList").innerHTML + "<li onclick=displayDetails(" + json.results[i].id + ");>" + json.results[i].title + "</li>";
			}
		}
	};
	//Clear the movie details from last search
	document.getElementById("movieHeading").style.display = "none";
	document.getElementById("movieImage").style.display = "none";
	document.getElementById("movieDetails").innerHTML = "";
	xhr.send(null);
}
/*Displays the details for the Selected Movie*/
function displayDetails (movieId) {
	document.getElementById("movieHeading").style.display = "block";
	document.getElementById("movieDetails").innerHTML = "";
	var xhrDetails = new XMLHttpRequest();
        xhrDetails.open("GET", "proxy.php?method=/3/movie/" + movieId);
        xhrDetails.setRequestHeader("Accept","application/json");
        xhrDetails.onreadystatechange = function () {
		if (this.readyState == 4) {
			var jsonDetails = JSON.parse(this.responseText);
			document.getElementById("movieImage").style.display = "block";
			if(jsonDetails.poster_path != "")
				displayPoster(jsonDetails.poster_path);
			var node;
			node = document.createElement("h4");
			if(jsonDetails.release_date != null)
				node.appendChild(document.createTextNode(jsonDetails.title + " (" + jsonDetails.release_date.substring(0,4) + ")")) ;
			else
				node.appendChild(document.createTextNode(jsonDetails.title));
			document.getElementById("movieDetails").appendChild(node);
			if(jsonDetails.tagline != "") {
				node = document.createElement("b");
				node.appendChild(document.createTextNode("Tagline: "));
				nodeParent = document.createElement("p");
				nodeParent.appendChild(node);
				nodeParent.appendChild(document.createTextNode(jsonDetails.tagline));
				document.getElementById("movieDetails").appendChild(nodeParent);
			}
			if(jsonDetails.overview != "") {
				node = document.createElement("b");
				node.appendChild(document.createTextNode("StoryLine: "));
				nodeParent = document.createElement("p");
				nodeParent.appendChild(node);
				nodeParent.appendChild(document.createTextNode(jsonDetails.overview));
				document.getElementById("movieDetails").appendChild(nodeParent);
			}
			if(jsonDetails.genres != "") {
				var concatGenre = "";
				for(i=0; i<jsonDetails.genres.length; i++) {
					concatGenre = concatGenre + jsonDetails.genres[i].name ;
					if(i != jsonDetails.genres.length-1)
						concatGenre = concatGenre + ", " ;
				}
				node = document.createElement("b");
				node.appendChild(document.createTextNode("Genres: "));
				nodeParent = document.createElement("p");
				nodeParent.appendChild(node);
				nodeParent.appendChild(document.createTextNode(concatGenre));
				document.getElementById("movieDetails").appendChild(nodeParent);
			}
		}
	};
	xhrDetails.send(null);
	var xhrCredits = new XMLHttpRequest();
	xhrCredits.open("GET", "proxy.php?method=/3/movie/" + movieId + "/credits");
        xhrCredits.setRequestHeader("Accept","application/json");
        xhrCredits.onreadystatechange = function () {
		if (this.readyState == 4) {
			var jsonCredits = JSON.parse(this.responseText);
			if(jsonCredits.cast != "") {
				var concatCast = "";
				var maxCast=5;
				if(jsonCredits.cast.length <5)
					maxCast = jsonCredits.cast.length;
				for(i=0; i<maxCast; i++) {
					concatCast = concatCast + jsonCredits.cast[i].name;
					if(i != maxCast-1)
						concatCast = concatCast + ", " ;
				}
			}
			node = document.createElement("b");
			node.appendChild(document.createTextNode("Cast: "));
			nodeParent = document.createElement("p");
			nodeParent.appendChild(node);
			nodeParent.appendChild(document.createTextNode(concatCast ));
			document.getElementById("movieDetails").appendChild(nodeParent);
		}
	};
	xhrCredits.send(null);
}
/*Displays the poster for the Selected Movie*/
function displayPoster (posterPath) {
	var xhrPoster = new XMLHttpRequest();
        xhrPoster.open("GET", "proxy.php?method=/3/configuration");
        xhrPoster.setRequestHeader("Accept","application/json");
        xhrPoster.onreadystatechange = function () {
		if (this.readyState == 4) {
			var jsonPoster = JSON.parse(this.responseText);
			document.getElementById("movieImage").src = jsonPoster.images.base_url + jsonPoster.images.poster_sizes[1] + posterPath;
		}
	};
	xhrPoster.send(null);
}