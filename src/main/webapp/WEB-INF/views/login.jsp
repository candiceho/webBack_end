<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/login.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
</head>

<body>

<div class="main">

<form  action="<c:url value='/login' />"  method="post" >
		  		
				
				<p>
					<label	for="username"  cssErrorClass="error">User Name:</label><br/>
					<input type="text"  name="username"/> <form:errors path="userName" />			
				</p>
				<p>	
					<label for="password"  cssErrorClass="error">Password:</label><br/>
					<input type="password" name="password" /> <form:errors path="password" />
				</p>
				
					<input type="submit" class="btn btn-info" value="Log In"/>
			
</form>

<div>
			<a href="register">New User?<br/>Please register here...<span class="glyphicon glyphicon-hand-right"></span></a> 
		</div>
		  <c:if test="${error == 'yes'}">
	                    <div >
        					<strong>UserName or Password is wrong!</strong>
    					</div>
	                </c:if>
</div>
</body>
</html>