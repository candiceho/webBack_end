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
<title>Cart Page</title>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<style>
body{	font-family: arial;	background-color:#ffff6f;}	
#products{font-size:28px; color:#ff5809; margin-top:30px; margin-bottom:20px;}
#total span{font-size:35px; font-weight:bold; color:#ff8040; }
#total{float:right;}
#back{float:right;}
#pro{color:#7e3d76; font-weight:bold; padding-top:20px;}
#back{color:#5151a2; font-weight:bold; font-size:20px;}
</style>

</head>

<body>



<div class="container">
<div class="row">
<div class="col-lg-12">

			<div id="products">Shopping Cart</div>          <div id="back"> <a href="/project/buyer"><span class="glyphicon glyphicon-hand-left"></span>back</a></div>	
		<form:form modelAttribute="order" action="/project/buyer/${cart.id}/placeOrder" method="post">
			    
			<table class="table table-striped" id="tab">
				<c:forEach items="${cart.products}" var="product">
					<tr>
						<td>
							<img src="/project/buyer/products/${product.id}/photo"  width="100" height="100">
						</td>
						<td id="pro">ProductName: ${product.productName}<br/>Price: $ ${product.price}<br/>Description: ${product.description}</td>
						
						<td><a href="/project/buyer/${cart.id}/${product.id}/deleteCart"><span class="glyphicon glyphicon-remove"></span>Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		
		<div id="total"><span>Total: $ ${cart.total} </span>
		<button class="btn btn-success" type="submit" >Place Order</button></div>
		</form:form>
</div>
</div>  <!--row end-->
</div>  <!--container end-->



	
</body>
</html>