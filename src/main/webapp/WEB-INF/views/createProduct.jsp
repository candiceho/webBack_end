<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/product.css" > 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="//cdn.ckeditor.com/4.5.6/standard/ckeditor.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style>
body{font-family:arial; background-color:#ffff6f;}
.edi{width:300px;}
.mainform{width:50%; position:relative; top: 30px; left:400px;}
</style>
</head>
<body>

<div class="mainform">
<form:form  modelAttribute="products" action="/project/vender/add" method="post" enctype="multipart/form-data">
<div> <form:label	for="productName" path="productName" cssErrorClass="error"></form:label><br/>
	<form:input type="text" placeholder="ProductName" path="productName" /> <br/> <form:errors path="productName" />			
</div>
<div> <form:label	for="price" path="price" cssErrorClass="error"></form:label><br/>
	<form:input type="text" placeholder="Price" path="price" /><br/> <form:errors path="price" />		
</div>
<div> <form:label	for="description" path="description" cssErrorClass="error"></form:label><br/>
	<form:textarea  placeholder="Description" class="form-control ckeditor edi" path="description"  /> <br/><form:errors path="description" />			
</div>
<div>
	<form:label for="photo" path="photo" cssErrorClass="error">Product Photo:</form:label><br/>
    <form:input path="photo" type="file" /> <form:errors path="photo" /><br/>
</div>	
	<input class="btn btn-info" value="submit" type="submit" />
					
</form:form>
</div>


				
				
				
				
</body>
</html>