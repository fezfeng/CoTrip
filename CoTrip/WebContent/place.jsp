<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String name = request.getParameter("name");
	String lat = request.getParameter("lat");
	String lng = request.getParameter("lng");
	String price = request.getParameter("price");
	String rating = request.getParameter("rating");
	String photo = request.getParameter("photo"); 
	String reference = request.getParameter("reference");
	String username = request.getParameter("username");
	String type  =request.getParameter("type");
%>
<title><%= "Welcome to"+" "+name%></title>
<link rel="stylesheet" type="text/css" href="mystyle.css" />
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<script>
var innerHTML="";
var map;
var address="";
var url="";
function initialize() {
	  var lat = "<%= lat%>";
	  var lng = "<%= lng%>";
	  var pyrmont = new google.maps.LatLng(lat,lng);
	  map = new google.maps.Map(document.getElementById('map-canvas0'), {
	    center: pyrmont,
	    zoom: 15
	  });
	  createMarker(pyrmont);
	  getDetails();
	}
	
	function getDetails(){
		var myreference = "<%= reference%>";
		var request = {
			  reference: myreference
		};
		var service = new google.maps.places.PlacesService(map);
		service.getDetails(request, callbackD);	
	}
	function callbackD(place, status) {
		if (status == google.maps.places.PlacesServiceStatus.OK) {
			   
			  
				
			   innerHTML+=place.formatted_address+"<br/><br/>";
			   innerHTML+= "<INPUT id=\"address\" type=\"hidden\" size=\"4\" name=\"address\" value="+place.formatted_address+">";
			   address = place.formatted_address;
				
			   

			   innerHTML+=place.formatted_phone_number+"<br/><br/>";
			   innerHTML+="<a href=\""+place.url+"\">"+place.url+"</a>"+"<br/><br/>";
			   
			   
			   
			   innerHTML+="<a href=\""+place.website+"\">"+place.website+"</a>"+"<br/><br/>";
			   innerHTML+= "<INPUT id=\"url\" type=\"hidden\" size=\"4\" name=\"url\" value="+place.website+">";
			   url = place.website;
			   
			   
			   if(place.reviews!=null){
				   innerHTML+="<h1 align=\"center\">Comments</h1>";
				   var j =0;
				   for(var i = 0; i < place.reviews.length;i++){
						  if(i>2) break;
						  j = i+1;
						  innerHTML+="<p>Voice:"+j+"</p>";
						  innerHTML+=place.reviews[i].text+"<br/>";
				   }
			   }
			  
		}
		
		var createtrip = document.getElementById("createtrip");
		
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "place_name");
		input.setAttribute("value", "<%= name%>");
		createtrip.appendChild(input);
		
		input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "place_type");
		input.setAttribute("value", "<%= type%>");
		createtrip.appendChild(input);
		
		input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "username");
		input.setAttribute("value", "<%= username%>");
		createtrip.appendChild(input);
		
		input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "rating");
		input.setAttribute("value", "<%= rating%>");
		createtrip.appendChild(input);
		
		
		
		
		input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "address");
		input.setAttribute("value", address);
		createtrip.appendChild(input);
		
		
		
		input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "place_url");
		input.setAttribute("value", url);
		createtrip.appendChild(input);	
		
		
		
		document.getElementById("showResult").innerHTML = innerHTML;
	}
	function createMarker(pos) {
		  
		  var marker = new google.maps.Marker({
		    map: map,
		    position: pos,
		    
		  });
	}
	google.maps.event.addDomListener(window, 'load', initialize);
	
</script>

</head>

<body>
	
	
	
	<h1 align="center"><%= name%></h1>
	<p align="center"><img src ="<%= photo%>"/></p>
	<p align="center">Price Level: <%=price %></p>
	<p align="center">Rating: <%=rating %></p>
	
	<div id="showResult" align="center"></div>
	
	<div id="map-canvas0" class="MapBody0"></div>
	
	<form action="trip.jsp" id="createtrip" method="GET">
		<div style="width:100%;text-align:center">
			<input type="submit" value="CreateTrip" />
		</div>
	</form>
	
	
	
	
	
</body>
</html>