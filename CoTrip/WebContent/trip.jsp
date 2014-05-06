<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mydb.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	// get parameters	

	String place_name = request.getParameter( "place_name" );
	String place_type = request.getParameter( "place_type" );
	String address  = request.getParameter( "address" );
	String place_url  = request.getParameter( "place_url" );
	String rating  = request.getParameter( "rating" );
	String username = request.getParameter( "username" );
	/*
	if(place_name == null || username == null) {
		response.sendRedirect("index.jsp");
	}
	*/
%>

<script>

	var place_name = "<%= place_name %>";
	//window.alert("place_name="+place_name);
	var place_type = "<%= place_type %>";
	//window.alert("place_type="+place_type);
	var address = "<%= address %>";
	//window.alert("address="+address);
	var place_url = "<%= place_url %>";
	//window.alert("place_url="+place_url);
	var rating = "<%= rating %>";
	//window.alert("rating="+rating);
	var username = "<%= username %>";
	//window.alert("username="+username);
	
</script>

<h3><font face="verdana">
<script type="text/javascript">
document.write(" Please fill in the information below, to create a new trip to " + place_name)
</script>
</font></h3>

<form action="tripservlet" id="trip_form" method="post" onsubmit="return reg(this)">

<p>A unique name for the trip: </p>
<input type="text" name="trip_name">
<br>

<br>
Day:
<select name="trip_day" id="trip_day">
</select>

<script type="text/javascript">

	var select_trip_day = document.getElementById("trip_day");
	var option;
	var _text;
	
	for(var i=1; i<32; i++)
	{
		option = document.createElement("option");
		option.setAttribute("value",i);
		_text = document.createTextNode(i);
		option.appendChild(_text);
		select_trip_day.appendChild(option);
	}

</script>



Month:
<select name="trip_month" id="trip_month">
</select>

<script type="text/javascript">

	var select_trip_month = document.getElementById("trip_month");
	
	for(var i=1; i<13; i++)
	{
		option = document.createElement("option");
		option.setAttribute("value",i);
		_text = document.createTextNode(i);
		option.appendChild(_text);
		select_trip_month.appendChild(option);
	}

</script>

Year:
<select name="trip_year" id="trip_year">
</select>

<script type="text/javascript">

	var select_trip_year = document.getElementById("trip_year");
	
	for(var i=2000; i<2032; i++)
	{
		option = document.createElement("option");
		option.setAttribute("value",i);
		_text = document.createTextNode(i);
		option.appendChild(_text);
		select_trip_year.appendChild(option);
	}

</script>

<br>
<p>Comment: </p>
<textarea rows="4" cols="50" name="comment"></textarea>

<br>

<br>
<p>Score the trip:</p>
<select name="score">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>

<br>
<br>
<p>All the friends list</p>
<table id="accept_table" border="0">
<tr>
<td></td><td></td>
</tr>
</table>
<br>
<br>

<center> <input type="submit" value=" Submit " > </center>
</form>

<script type="text/javascript">

	var trip_form = document.getElementById("trip_form");
	
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "place_name");
	input.setAttribute("value", place_name);
	trip_form.appendChild(input);
	
	input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "place_type");
	input.setAttribute("value", place_type);
	trip_form.appendChild(input);
	
	input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "address");
	input.setAttribute("value", address);
	trip_form.appendChild(input);
	
	input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "place_url");
	input.setAttribute("value", place_url);
	trip_form.appendChild(input);
	
	input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "rating");
	input.setAttribute("value", rating);
	trip_form.appendChild(input);
	
	input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "username");
	input.setAttribute("value", username);
	trip_form.appendChild(input);
	
	var accept_table = document.getElementById("accept_table");
	
	<%
	
	UserManager UM = new UserManager();
	List<String> usernames = UM.readAllUsernames();
	
	if(usernames != null) {
		for(int i=0; i<usernames.size(); i++) {
			if (usernames.get(i).equals(username))
				continue;
	%>
			var tr = document.createElement("tr");
			var td_check = document.createElement("td");
			var td_label = document.createElement("td");
			
			var checkbox = document.createElement("input");
			checkbox.type = "checkbox";
			checkbox.name = "invite_list";
			checkbox.value = "<%= usernames.get(i) %>";
			checkbox.id = "id";
			
			var label = document.createElement('label')
			label.htmlFor = "id";
			label.appendChild(document.createTextNode("<%= usernames.get(i) %>"));
			
			
			td_check.appendChild(checkbox);
			td_label.appendChild(label);
			
			tr.appendChild(td_check);
			tr.appendChild(td_label);
			
			accept_table.appendChild(tr);
	<%
		}
	}
	%>
	


function reg(form) {
	if (form.trip_name.value == "") {
		alert("Trip Name can't be empty");
		return false;
	}
}

</script>



</body>
</html>

