<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Orders Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/orders.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<style>
body{ background-color: #ffff6f;}
#products{font-size:28px; color:#ff5809; margin-top:30px; margin-bottom:20px;}
#back{float:right; font-size:20px;}
tr {background-color: #ffdcb9;}
#first{ font-weight:bold; color:#ffa042;}
#to{ font-size:24px; float: right; color:#f75000; margin-top:-10px;}
</style>
</head>

<body>
<div class="container">
<div class="row">
<div class="col-lg-12">
			<div id="products">Your Orders</div>          <div id="back"> <a href="/project/buyer"><span class="glyphicon glyphicon-hand-left"></span>back</a></div>	

	<c:forEach items="${orderlist}" var="order">
	<table class="table table-striped" id="tab">
	<tr><th>OrderId</th><th>Finished</th><th colspan="10">Items</th></tr>
			<tr id="first"><td>
			 ${order.id} 
			</td>
			<td> ${order.status} </td>
			<c:forEach items="${order.products}" var="product"><td>
				<img src="/project/buyer/products/${product.id}/photo" width="80" height="80" /> <br/> ProductName: ${product.productName} <br/> Price: $ ${product.price}<br/> ${product.description}</td></c:forEach>
			</tr>
				
				
					
			</table>		
					<div id="to"><span class="glyphicon glyphicon-shopping-cart"></span>$ ${order.total}</div>	
	</c:forEach>
		

</div>
</div>  <!--row end-->
</div>  <!--container end-->



	
</body>
</html>