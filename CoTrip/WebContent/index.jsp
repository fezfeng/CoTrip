<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mydb.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1 align="center">CoTrip!</h1>
<form action="loginservlet" method="post" onsubmit="return login(this)">
<div align="center">
Username: <input type="text" name="Username" />
<br />
<br />
Password: <input type="password" name="Password" />
<br/>
<br/>
<input type="submit" value="Login"/>
</div>
</form>

<h1 align="center">Do not have an account? register now!</h1>

<form action="registerservlet" method="post" onsubmit="return reg(this);">
<div align="center">
Username: <input type="text" name="username" />
<br />
<br />
Password: <input type="password" name="password" />
<br />
<br />
Confirm : <input type="password" name="confirm_password" />
<br />
<br />
City: <select name="city" id="city">
	<option value="Boston" selected="selected">Boston</option>
	<option value="Los Angeles">Los Angeles</option>
	<option value="New York">New York</option>
	<option value="San Francisco">San Francisco</option>
	</select>
<br/>
<br/>
<input type="submit" value="Register"/>
</div>
</form>

<script type="text/javascript">
	function reg(form){
		
		if(form.username.value == ""){
			alert("Username couldn't be null!");
			return false;
		}
		if(form.password.value == ""){
			alert("Password couldn't be null!");
			return false;
		}
		if(form.password.value != form.confirm_password.value){
			alert("Password couldn't be different!");
			return false;
		}
		
	}
	
	function login(form){
		if(form.Username.value == ""){
			alert("Username couldn't be null!");
			return false;
		}
		if(form.Password.value == ""){
			alert("Password couldn't be null!");
			return false;
		}
	}
</script>


</body>
</html>