<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Guest Product Page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" 
					  rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" 
					  crossorigin="anonymous">
		<link rel = "stylesheet" type = "text/css" href = "../css/style.css">
	</head>
	<body>
		
		<div class = "header">
			<form class = "log-out"  action = "/logout" method = "post">
				<input type="submit"   class="btn btn-primary " value="Log Out">
			</form>
		</div>
		
		<div class="jumbotron text-center">
		
			<h1>SHEA</h1>
			<h1 class = "welcome">Welcome ${theguest.getName()}</h1>
			
			<%-- <div class = "user-login-register">
				<form class = "button-form" action = "/logout" method = "post">
					<input type="submit"   class="btn btn-primary" value="Log Out">
				</form><br>
				<form class = "button-form" action = "/guestaccount" method = "post">
					<input type="submit"  class="btn btn-primary" value="Edit my Account">
				</form>
				
			</div> --%>
			
			
			
			
			
			<div class="product-on-shelf" >
				
				<h5 >What do you want to buy?</h5>
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
				        <c:forEach items="${displayProductBidNot}" var="eachprod" varStatus="loop">
				        <tr>
				        	<td><c:out value = "${loop.index+1}"></c:out></td>
				        	<td><a href = "proddetail/${eachprod.getId()}"><c:out value = "${eachprod.getProdName()}"></c:out></a></td>
				        	<td><c:out value = "${eachprod.getCategory()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getBrand()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getMadeDate()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getDamageSeverity()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getCurrentPrice()}"></c:out></td>
				        	<td><a href = "bid/${theguest.getId()}/${eachprod.getId()}"><c:out value = "${eachprod.getBidPrice()}"></c:out></a></td>
				        	<td><c:out value = "${eachprod.getState()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getOwner().getName()}"></c:out></td>
				        	<td><fmt:formatDate value="${eachprod.getExpireDate()}" pattern="yyyy-MM-dd" /></td>
				        </tr>
				        </c:forEach>
				        <tr>
				        	<td></td>
				        	<td></td>
				        	<td></td>
				        	<td></td>
				        	<td></td>
				        	<td>Your Bidding</td>
				        	<td></td>
				        	<td></td>
				        	<td></td>
				        	<td></td>
				        	<td></td>
				        </tr>
				        <c:forEach items="${displayProductBid}" var="eachprod" varStatus="loop">
				        
				        <tr>
				        	<td><c:out value = "${loop.index+1}"></c:out></td>
				        	<td><a href = "proddetail/${eachprod.getId()}"><c:out value = "${eachprod.getProdName()}"></c:out></a></td>
				        	<td><c:out value = "${eachprod.getCategory()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getBrand()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getMadeDate()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getDamageSeverity()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getCurrentPrice()}"></c:out></td>
				        	<td><a href = "bid/${theguest.getId()}/${eachprod.getId()}"><c:out value = "${eachprod.getBidPrice()}"></c:out></a></td>
				        	<td><c:out value = "${eachprod.getState()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getOwner().getName()}"></c:out></td>
				        	<td><fmt:formatDate value="${eachprod.getExpireDate()}" pattern="yyyy-MM-dd" /></td>
			      
				        </tr>
				        </c:forEach>
			    	</tbody>
		   		</table> 	
			</div>
			
			<%-- <div class="product-on-shelf" >
				
				<h5 >Your bidding Product?</h5>
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
				        <c:forEach items="${displayProductBid}" var="eachprod" varStatus="loop">
				        <tr>
				        	<td><c:out value = "${loop.index+1}"></c:out></td>
				        	<td><a href = "proddetail/${eachprod.getId()}"><c:out value = "${eachprod.getProdName()}"></c:out></a></td>
				        	<td><c:out value = "${eachprod.getCategory()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getBrand()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getMadeDate()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getDamageSeverity()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getCurrentPrice()}"></c:out></td>
				        	<td><a href = "bid/${theguest.getId()}/${eachprod.getId()}"><c:out value = "${eachprod.getBidPrice()}"></c:out></a></td>
				        	<td><c:out value = "${eachprod.getState()}"></c:out></td>
				        	<td><c:out value = "${eachprod.getOwner().getName()}"></c:out></td>
				        	<td><fmt:formatDate value="${eachprod.getExpireDate()}" pattern="yyyy-MM-dd" /></td>
			      
				        </tr>
				        </c:forEach>
			    	</tbody>
		   		</table> 	
			</div> --%>
		</div>
	
	
	</body>
</html>