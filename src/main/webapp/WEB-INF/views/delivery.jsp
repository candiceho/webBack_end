<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delivery Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/vender.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<style>
 body{background-color: #ffff6f; font-family:arial;}
#pro{color:#7e3d76; font-weight:bold; padding-top:20px;}
</style>
</head>

<body>
<div class="container">
<div class="row">
<div class="col-lg-4">
<div  id="pic"><img class="img-circle" src="/project/delivery/user/${user.id }/photo" height="200" width="200" /></div>

<div class="name1"><span id="wel">Welcome</span> <span id="name2"> ${user.firstName } , ${user.lastName }<br/> Role: ${user.role}</span></div>

<ul>
      <li><a href="<c:url value='/logout'/>"><span class="glyphicon glyphicon-user"></span> <strong>Logout</strong></a></li>

</ul>
</div>


<div class="col-lg-8">
			<div id="products">All Orders</div>
		
          <div class="orderSta">Unfinished Orders</div>
		<c:if test="${!empty unFinishList}">
		    <table class="table table-striped" id="tab">
			<tr><th>OrderId</th><th>Total Price</th><th>UserId</th><th>Status</th></tr>
		     <c:forEach items="${unFinishList}" var="unfinishOrder">				
					<tr>
					   <form action="/project/delivery/${unfinishOrder.id}/finish" method=post>  
						<td class="proo">
							${unfinishOrder.id}
						</td>
						<td class="proo">$ ${unfinishOrder.total}</td>
						<td class="proo"> ${unfinishOrder.user.getId()}</td>
						<td><input type="submit" class="btn btn-warning"value="finished" /></td>						
					  </form>
					</tr>				
		   </c:forEach>
			</table>
		</c:if>
		
	<hr/>
		   <div class="orderSta">finished Orders</div>
				<c:if test="${!empty finishList}">
			<table class="table table-striped" id="tab">
			<tr><th>OrderId</th><th>Total Price</th><th>UserId</th></tr>
				<c:forEach items="${finishList}" var="finishOrder">
					<tr>
						<td class="proo">${finishOrder.id}</td>
					     <td class="proo">$ ${finishOrder.total}</td>
					     <td class="proo"> ${finishOrder.user.getId()}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>		
</div>
</div>  <!--row end-->
</div>  <!--container end-->
	
</body>
</html>