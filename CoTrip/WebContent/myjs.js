var map;
var infowindow;
var markersArray = [];
var showResult="";
var cnt = 0;

function initialize() {
	
  
  var pyrmont = new google.maps.LatLng(42.4, -71);
  map = new google.maps.Map(document.getElementById('map-canvas'), {
    center: pyrmont,
    zoom: 10
  });
  
  google.maps.event.addListener(map, 'click', function(event){placeMarker(event.latLng);});
  /*
  var request = {
    location: pyrmont,
    radius: 100000,
    types: ['store']
  };
  infowindow = new google.maps.InfoWindow();
  var service = new google.maps.places.PlacesService(map);
  service.nearbySearch(request, callback);*/
}

function changeLocation(){
	var location = document.getElementById("citychosen");
	
	switch(location.value){
		case "Los Angeles":
			var pyrmont = new google.maps.LatLng(34.05, -118.24);
			break;
		case "New York":
			var pyrmont = new google.maps.LatLng(40.69, -73.95);
			break;
		case "San Francisco":
			var pyrmont = new google.maps.LatLng(37.77, -122.42);
			break;
		case "Boston":
			var pyrmont = new google.maps.LatLng(42.4, -71);
			break;
		default:
	}
	
	  map = new google.maps.Map(document.getElementById('map-canvas'), {
	    center: pyrmont,
	    zoom: 10
	  });
	  
	  google.maps.event.addListener(map, 'click', function(event){placeMarker(event.latLng);});
}

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location,
    
  });

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent(place.name);
    infowindow.open(map, this);
  });
  
  markersArray.push(marker);
}

function placeMarker(location){
	var marker = new google.maps.Marker({position:location,map:map});
	google.maps.event.addListener(marker,'rightclick',function(){marker.setMap(null);});
	google.maps.event.addListener(marker,'click',function(event){getPlaces(event.latLng);});
	
	
}

function removeMarkers(){
	for (var i = 0; i < markersArray.length; i++ ) {
	    markersArray[i].setMap(null);
	  }
	  markersArray.length = 0;
}

function getPlaces(location){
	var type = document.getElementById("typechosen");
	
	var request = {
		    location: location,
		   // radius: 5000,
		    rankBy: google.maps.places.RankBy.DISTANCE,
		    types: [type.value]
		  };
		  infowindow = new google.maps.InfoWindow();
		  service = new google.maps.places.PlacesService(map);
		  service.nearbySearch(request, callback);
}

function callback(results, status) {
	  if (status == google.maps.places.PlacesServiceStatus.OK) {
		 
		showResult = "<h1>The Results</h1>";
		var username = document.getElementById("username");
		var type = document.getElementById("typechosen");
		
	    for (var i = 0; i < results.length; i++) {
	      createMarker(results[i]);
	      
	      var tmp_name = results[i].name;
	      var tmp_price_level = (results[i].price_level==null)?0:results[i].price_level;
	      var tmp_rating = (results[i].rating == null)?0:results[i].rating;
	      var tmp_lat = results[i].geometry.location.lat();
	      var tmp_lng = results[i].geometry.location.lng();
	      var tmp_photo = "";
	      var tmp_reference = results[i].reference;
	      if(results[i].photos!=null){
	    	  tmp_photo = results[i].photos[0].getUrl({'maxWidth':500, 'maxHeight':500});
	      }else{
	    	  tmp_photo = results[i].icon;
	      }
	      
	      var href = "<a href =\"place.jsp"+"?name="+tmp_name+"&"+"username="+username.value+"&"+"type="+type.value+"&"+"lat="+tmp_lat+"&"+"lng="+tmp_lng+"&"+"price="+tmp_price_level+"&"+"rating="+tmp_rating+"&"+"photo="+tmp_photo+"&"+"reference="+tmp_reference+"\">"+results[i].name+"</a><br/>";
	      showResult += href;
	      
	    }
	    
	    document.getElementById("showResult").innerHTML = showResult;
	  }
	 
	}
 



google.maps.event.addDomListener(window, 'load', initialize);