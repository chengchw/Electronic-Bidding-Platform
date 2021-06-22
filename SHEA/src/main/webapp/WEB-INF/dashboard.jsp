<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Dash Board</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" 
					  rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" 
					  crossorigin="anonymous">
		<link rel = "stylesheet" type = "text/css" href = "../css/style.css">
	</head>
	<body>
	
		<div class="jumbotron text-center" >
		
			<h1>SHEA</h1>
			<h2>Welcome to SHEA, let's save the world</h2>
			<div class = "intro">
				<p>You can buy or sell any used electronic consumer device here</p>
				<p>We aim to save the world and maximize the usage of functional electronic device</p>	
			</div>
			
			<div class = "user-login-register">
			
				<form class = "button-form" action = "/guest" method = "post">
					<input type="submit" class="btn btn-primary"  value="I am Guest">
				</form>
				
				<form class = "button-form" action = "/provider" method = "post">
					<input type="submit" class="btn btn-primary" value="I am Provider">
				</form>		
			
			</div>
			
			<div class="product-on-shelf" >
			
				<h5 class = "onshelf">Product On Shelf !!!!</h5>
				<table class="table table-hover">
				    <thead>
				        <tr>
				            <th>Index</th>
				            <th>Product</th>
				            <th>Category</th>
				            <th>Brand</th>
				            <th>Made Year</th>
				            <th>Damage Rate</th>
				            <th>Current Price</th>
				            <th>Bidding Amount</th>
				            <th>Location</th>
				            <th>Seller</th>	
				            <th>End Date</th>         
				        </tr>
				    </thead>
				    <tbody>
				    <!-- product list -->
				        <c:forEach items="${displayProduct}" var="eachprod" varStatus="loop">
				        <tr>
				        	<td><c:out value = "${loop.index+1}"></c:out></td>
				        	<td><c:out value = "${eachprod.getProdName()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getCategory()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getBrand()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getMadeDate()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getDamageSeverity()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getCurrentPrice()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getBidPrice()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getState()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getOwner().getName()}"></c:out></td>
				        	<td><fmt:formatDate value="${eachprod.getExpireDate()}" pattern="yyyy-MM-dd" /></td>
				        	
				        	
				            <%-- <td><a href = "#" ><c:out value = "${event.getEvent()}"></c:out></a></td>
				            <td><c:out value="${event.getEventdate()}"/></td>
				            <td><c:out value= "${event.getLocation()}"/></td>
				            <td><c:out value= "${event.getHost().getFirstname()}"/></td>   
				            
				            <td>
				            <c:choose>
				            	<c:when test = "${event.getHost().getId() == theuser}">
					            	<a href = "#">Edit</a>
					            	<a href = "#">Delete</a>
				            	</c:when>
				            	
				            	<c:when test = "${event.getJoinusers().contains(theuser)}">
					            	<p>Joining</p>
					            	<a href = "#">Cancel</a>
				            	</c:when>
				            	
				            	<c:otherwise>
				            		<a href = "#">Join</a>
				            	</c:otherwise>
				            </c:choose>
				            </td>      --%>
				        </tr>
				        </c:forEach>
			    	</tbody>
		   		</table>
				    
	
				
			
			
			
			
			</div>
			
			
			
			
			
		
		
		</div>
	
	</body>
</html>