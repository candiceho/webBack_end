<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buyer Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/vender.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<style>
 body{background-color: #ffff6f; font-family:arial;}
#pro{color:#7e3d76; font-weight:bold; padding-top:70px;}
</style>
</head>

<body>
<div class="container">
<div class="row">
<div class="col-lg-4">
<div  id="pic"><img class="img-circle" src="buyer/user/${user.id}/photo" height="200" width="200" /></div>

<div class="name1"><span id="wel">Welcome</span> <span id="name2"> ${user.firstName } , ${user.lastName }<br/> Role: ${user.role}</span></div>

<ul>
      <li><a href="<c:url value='/logout'/>"><span class="glyphicon glyphicon-user"></span> <strong>Logout</strong></a></li>
  	 <li role="presentation"><a href="buyer/${user.id}/cart"><span class="glyphicon glyphicon-search"></span><strong>View Your Cart</strong></a>     
  	                 <c:if test="${error == 'emptyy'}">
	                    <span>
        					<strong>Your Cart is Empty!</strong>
    					</span>
	                </c:if></li>
  	               
	 <li role="presentation"><a href="buyer/${user.id}/order"><span class="glyphicon glyphicon-eye-open"><span><strong>View Your Orders</strong></a></li>
</ul>
</div>

<div class="col-lg-8">
			<div id="products">All Products</div>
		

		<c:if test="${!empty productsList}">
			<table class="table table-striped" id="tab">
				<c:forEach items="${productsList}" var="products">
					<tr>
						<td>
							<img src="buyer/products/${products.id}/photo" height="200" width="200" />
						</td>
						<td id="pro">ProductName: ${products.productName}<br/>Price: $ ${products.price}<br/>Description: ${products.description}</td>
						
						<td><a href="buyer/${user.id}/${products.id}/addCart"><span class="glyphicon glyphicon-remove"></span>Add to Cart</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		
</div>
</div>  <!--row end-->
</div>  <!--container end-->




		
</body>
</html>