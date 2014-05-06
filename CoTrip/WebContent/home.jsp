<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mydb.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div align="right">
<form action="index.jsp" id="logout" method="get">
<input type="submit" value="logout">
</form>
</div>

<% 
	String username =(String)session.getAttribute( "user" );
	if(username==null) response.sendRedirect("index.jsp");
%>

<script>
	var username = "<%= username %>";
	
</script>

<center><h3><font face="verdana">
<script type="text/javascript">
document.write(" This is " + username + "'s home page, welcome here");
</script>
</font></h3>
</center>









<%
	TripManager TM = new TripManager();
	List<Trip> recent_update_trips = new ArrayList<Trip>();
	recent_update_trips = TM.readRecentTrips();
	
	PlaceManager PM = new PlaceManager();
	UserManager UM = new UserManager();
	UserTripManager UTM = new UserTripManager();
	
%>












<%
	User user = new User();
	user = UM.getUserForName(username);
	
	List<UserTrip> user_trips = new ArrayList<UserTrip>();
	user_trips = UTM.readMyRecentTrips(user);
%>

<h4>
<script type="text/javascript">
document.write("My Recent trips: ");
</script>
</h4>

<table id="my_recent_table" border="1">
<tr>
<td>Place</td><td>Score</td><td>Date</td><td>Comment</td><td>Url</td>
</tr>
</table>

<script>

	var recent_table = document.getElementById("my_recent_table");
<%
	Trip trip = new Trip();
	Place place = new Place();

	if(user_trips != null) {
		for(int i=0; i<user_trips.size(); i++) {
%>
			var tr = document.createElement("tr");
			
			var td_place = document.createElement("td");
			var td_score = document.createElement("td");
			var td_date = document.createElement("td");
			var td_comment = document.createElement("td");
			var td_place_url = document.createElement("td");
	
<%
			trip = new Trip();
			trip = TM.readTripForId(user_trips.get(i).getTripId());
			
			
			place = new Place();
			place = PM.readPlaceForID(trip.getPlaceId());
%>
			td_place.innerHTML="<%= place.getName() %>";
			td_score.innerHTML="<%= user_trips.get(i).getScore() %>";
			td_date.innerHTML="<%= trip.getDate().toString() %>";
			td_comment.innerHTML="<%= user_trips.get(i).getComment() %>";
			td_place_url.innerHTML= "<a href=\""+"<%= place.getURL() %>"+"\">"+"<%= place.getURL() %>"+"</a>";;
			
			
			tr.appendChild(td_place);
			tr.appendChild(td_score);
			tr.appendChild(td_date);
			tr.appendChild(td_comment);
			tr.appendChild(td_place_url);
			
			recent_table.appendChild(tr);
<%
		}
	}
%>

</script>

<br>



















<h4>
<script type="text/javascript">
document.write(" AcceptList: ");
</script>
</h4>

<%
	AcceptListManager AM = new AcceptListManager();
	List<AcceptList> accept_lists = new ArrayList<AcceptList>();
	accept_lists = AM.readAcceptListForUser(user);
%>


<table id="title_table" border="1">
<tr>
<td width="75">Accept</td><td width="150">TripName</td><td width="100">Place</td>
<td width="80">Date</td>  <td width="25"> Score   </td><td width="160">Comment</td>
<td width="60">Submit</td>
</tr>
</table>

<div id="addtable"></div>


<script>

<%
	if (accept_lists != null) {
		for(int i=0; i<accept_lists.size(); i++) {
%>
			var form = document.createElement("form");
			form.setAttribute("action", "AcceptServlet");
			form.setAttribute("method", "post");
			
			var table = document.createElement("table");
			table.setAttribute("border", "1");
			
			var tr = document.createElement("tr");
			
			var td_accept = document.createElement("td");
			var td_tripname = document.createElement("td");
			var td_place = document.createElement("td");
			var td_date = document.createElement("td");
			var td_score = document.createElement("td");
			var td_comment = document.createElement("td");
			var td_submit = document.createElement("td");
			
			// Add accept column
			var accept = document.createElement("select");
			accept.setAttribute("name", "accept");
			
			var option_acc = document.createElement("option");
			option_acc.setAttribute("value", "<%= accept_lists.get(i).getId() %>");
			option_acc.innerHTML = "accept";
			accept.appendChild(option_acc);
			
			var option_rej = document.createElement("option");
			option_rej.setAttribute("value", "-"+"<%= accept_lists.get(i).getId() %>");
			option_rej.innerHTML = "reject";
			accept.appendChild(option_rej);
			
			td_accept.appendChild(accept);
			td_accept.setAttribute("width", "75");
			
			// Add trip name
			<%
			trip = new Trip();
			trip = TM.readTripForId(accept_lists.get(i).getTripId());
			
			place = new Place();
			place = PM.readPlaceForID(trip.getPlaceId());
			%>
			
			td_tripname.innerHTML="<%= trip.getTripName() %>";
			td_tripname.setAttribute("width", "150");

			// Add place column
			td_place.innerHTML="<%= place.getName() %>";
			td_place.setAttribute("width", "100");

			// Add date column
			td_date.innerHTML="<%= trip.getDate().toString() %>";
			td_date.setAttribute("width", "80");
			
			
			// Add score column
			var select = document.createElement("select");
			select.setAttribute("name", "score");
			
			for (var i=1; i<6; i++) {
				var option = document.createElement("option");
				option.setAttribute("value", i);
				option.innerHTML = i;
				select.appendChild(option);
			}
			td_score.appendChild(select);
			td_score.setAttribute("width", "25");
			
			// Add comment column
			var comment = document.createElement("input");
			comment.setAttribute("type", "text");
			comment.setAttribute("name", "comment");
			comment.setAttribute("value", "");
			comment.setAttribute("width", "200");
			td_comment.appendChild(comment);
			
			// Add submit column
			var submit = document.createElement("input");
			submit.setAttribute("type", "submit");
			submit.setAttribute("value", "submit");
			td_submit.appendChild(submit);
			td_submit.setAttribute("width", "60");
			
			tr.appendChild(td_accept);
			tr.appendChild(td_tripname);
			tr.appendChild(td_place);
			tr.appendChild(td_date);
			tr.appendChild(td_score);
			tr.appendChild(td_comment);
			tr.appendChild(td_submit);
			
			table.appendChild(tr);
			
			form.appendChild(table);
			
			document.getElementById("addtable").appendChild(form);
			
<%
		}
		
	}
%>

</script>











<br>
<br>
<br>
<br>
<br>
<center>
<form action="search.jsp" id="home_search_form" method="get">
<input type="submit" value="Start search">
</form>
</center>

<script type="text/javascript">

	var trip_form = document.getElementById("home_search_form");
	
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "username");
	input.setAttribute("value", username);
	trip_form.appendChild(input);
	
</script>

</body>
</html>