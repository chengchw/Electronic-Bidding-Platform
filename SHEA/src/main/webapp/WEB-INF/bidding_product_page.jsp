<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Bidding Product Page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" 
					  rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" 
					  crossorigin="anonymous">
		<link rel = "stylesheet" type = "text/css" href = "../css/style.css">
	</head>
	<body>
		<div class = "container">
			<h1>${theproduct.getProdName()}</h1>
			<p>Current Price is: ${theproduct.getCurrentPrice()}</p>
			<p>Minimum Bidding Amount is : ${theproduct.getBidPrice()}</p>
	    	<form  class="mb-3" method = "post" action = "/makebid/${theproduct.getId()}">	
				<p>${errormess}</p>
				<p>
					
					<label for = "bidprice">Bid Price:</label>
					
					<input type ="text" id = "email" name = "bidprice" class="mb-3"  value =  "${theproduct.getBidPrice()+theproduct.getCurrentPrice()}"/>
					<input type = "submit" class="btn btn-primary" value ="Bid!!"/>
				</p>
			</form>	
		</div>
	</body>
</html>