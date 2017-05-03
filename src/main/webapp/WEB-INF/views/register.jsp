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
<title>Register Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/register.css" > 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<style>
body{font-family:arial; background-color:#ffff6f;}
</style>
</head>
<body>

<div  class="information">
	                                
	
		<form:form modelAttribute="user" action="register" method="post" enctype="multipart/form-data">
		   <div class="container">
			  <div class="row">
			  <div class="col-lg-12 half" >
			  <h1>Register</h1>
				<div>	<form:label	for="userName" path="userName" cssErrorClass="error"><span>User Name:</span></form:label><br/>
					<form:input class="input" id="username" name="username" type="text" path="userName" /> <label class="error" id="usernameError"></label> <form:errors path="userName" />			
				</div>
				
				<div>	<form:label	for="password" path="password" cssErrorClass="error"><span>Password:</span></form:label><br/>
					<form:input class="input" id="password" name="password" type="password" path="password"  /> <label class="error" id="passwordError"></label><form:errors path="password" />			
				</div>
				
				<div>
					<label	for="repassword" ><span>Comfirm Password:</span></label><br/>
					<input class="input" id="repassword" name="repassword"  type="password" /> <label class="error" id="repasswordError"></label>			
				</div>
				
				<div>
					<label	for="email" ><span>email:</span></label><br/>
					<input class="input" id="email" name="email"  type="text" /> 	<label class="error" id="emailError"></label>		
				</div>
				
				
				
				<div>
					<form:label	for="firstName" path="firstName" cssErrorClass="error"><span>First Name:</span></form:label><br/>
					<form:input class="input" id="fname" name="fname" type="text" path="firstName" /><label class="error" id="fnameError"></label> <form:errors path="firstName" />			
				</div>
				<div>	
					<form:label for="lastName" path="lastName" cssErrorClass="error"><span>Last Name:</span></form:label><br/>
					<form:input class="input" id="lname" name="lname" type="text" path="lastName" /> <label class="error" id="lnameError"></label><form:errors path="lastName" />
				</div>
				
				<div>
					<form:label	for="role" path="role" cssErrorClass="error"><span>User Type:</span></form:label><br/>
					<form:select path="role" > 
					<form:option value="VENDER" />
					<form:option value="BUYER" />
					<form:option value="DELIVER" />
					</form:select>
					<form:errors path="role" />			
				</div>
				
				<div>
					<form:label for="photo" path="photo" cssErrorClass="error"><span>User Photo:</span></form:label><br/>
					<form:input path="photo" type="file" /> <form:errors path="photo" />
				</div>	
					
		  </div>
			  </div><!--row end-->
			  <input class="btn btn-primary" id="sub" name="sub" type="submit" value="SUBMIT"/>   
			  
			  </div><!--container end-->
				 
				 
		</form:form>

</div> 
<script>
$(function(){
$(".error").each(function(){
showError($(this));
});         // loop all error class 

$("#sub").hover(
function(){
$("#sub").css({"background":"#7373b9"});
},
function(){
$("#sub").css({"background":"#8f4586"});
});     



$(".input").focus(function(){       // when focus error hidden
var labelId=$(this).attr("id")+"Error";
$("#"+labelId).text("");  // clear label
showError($("#"+labelId));   // hidden label

});

$(".input").blur(function(){
var a= $(this).attr("id");
var b = "check"+a.substring(0,1).toUpperCase()+a.substring(1)+"()"; // function name 
eval(b);    // string is excuted as javascript code 
});




});



$("#F").submit(function(){   // when submit check all
aler(1);
var bool= true;
if(!checkUsername()){
bool=false;}

if(!checkPassword()){
bool=false;}

if(!checkRepassword()){
bool=false;}

if(!checkEmial()){
bool=false;}

if(!checkLname()){
bool=false;}

if(!checkFname()){
bool=false;}
alert(bool);

return bool;
});


//check username
function checkUsername(){
var id= "username";
var value=$("#"+id).val();
if(!value){       // empty?
$("#"+id+"Error").text("userName cannot be empty!");
showError($("#"+id+"Error"));
return false;
}
if(value.length<3 || value.length>20){       // length of username 3~20
$("#"+id+"Error").text("userName length must within 3~20!");
showError($("#"+id+"Error"));
return false;
}
return true;
}


// check password
function checkPassword(){
var id= "password";
var value=$("#"+id).val();
if(!value){       // empty?
$("#"+id+"Error").text("password cannot be empty!");
showError($("#"+id+"Error"));
return false;
}
if(value.length<3 || value.length>20){       // length of username 3~20
$("#"+id+"Error").text("password length must within 3~20!");
showError($("#"+id+"Error"));
return false;
}
return true;
}

// check confirm password 
function checkRepassword(){
var id= "repassword";
var value=$("#"+id).val();
if(!value){       // empty?
$("#"+id+"Error").text("confirm cannot be empty!");
showError($("#"+id+"Error"));
return false;
}
if(value!=$("#password").val()){       // 2 times match 
$("#"+id+"Error").text("password not match");
showError($("#"+id+"Error"));
return false;
}
return true;
}

// check email
function checkEmail(){
var id= "email";
var value=$("#"+id).val();
if(!value){       // empty?
$("#"+id+"Error").text("email cannot be empty!");
showError($("#"+id+"Error"));
return false;
}

if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){         // email format 
$("#"+id+"Error").text("please enter valid email format!");
showError($("#"+id+"Error"));
return false;
}
return true;
}

// check lastname
function checkLname(){
var id= "lname";
var value=$("#"+id).val();
if(!value){       // empty?
$("#"+id+"Error").text("lastname cannot be empty!");
showError($("#"+id+"Error"));
return false;
}
if(value.length<2 || value.length>20){       // length of username 3~20
$("#"+id+"Error").text("lastname length must within 3~20!");
showError($("#"+id+"Error"));
return false;
}
return true;
}

// check firstname
function checkFname(){
var id= "fname";
var value=$("#"+id).val();
if(!value){       // empty?
$("#"+id+"Error").text("firstname cannot be empty!");
showError($("#"+id+"Error"));
return false;
}
if(value.length<2 || value.length>20){       // length of username 3~20
$("#"+id+"Error").text("firstname length must within 3~20!");
showError($("#"+id+"Error"));
return false;
}
return true;
}


function showError(ele){
var text= ele.text();
if(!text){
ele.css({"display":"none"});}
else{
ele.css({"display":""});}}
</script>
</body>
</html>