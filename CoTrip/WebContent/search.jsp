<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mydb.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Google Places API</title>
<link rel="stylesheet" type="text/css" href="mystyle.css" />
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<script src="myjs.js" type="text/javascript"></script>

</head>

<body>
<%
	String username = request.getParameter("username");
%>
<INPUT id="username" type="hidden" size="4" name="username" value=<%= username%>>
<div id="map-canvas" class="MapBody"></div>
<div id="queryBox">
	<h1><%= "Hello"+" "+username+"!"%></h1>
	<table id="query">
		<tr>
			<td id="city">
				<div class="xLabel"> City: </div>
				<select id="citychosen" class="xSelect">
					<option value="Boston" selected="selected">Boston</option>
					<option value="Los Angeles">Los Angeles</option>
					<option value="New York">New York</option>
					<option value="San Francisco">San Francisco</option>
				</select>
				<input type="submit" value="Change" onclick="changeLocation();">
			</td>
		</tr>	
		<tr>
			<td id="Type">
				<div class="xLabel"> Type:</div>
				<select id="typechosen" class="xSelect">
					<option value="restaurant" selected="selected">restaurant</option>
					<option value="store">store</option>
					<option value="bar">bar</option>
					<option value="cafe">cafe</option>
					<option value="university">university</option>
					<option value="museum">museum</option>
					<option value="library">library</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="submit" value="ClearMap" onclick="removeMarkers();">
			</td>
		</tr>
						
	</table>
</div>

<div id = "showResult" class = "Result"></div>

</body>
</html>