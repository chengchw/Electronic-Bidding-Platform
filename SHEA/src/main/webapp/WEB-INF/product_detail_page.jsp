<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Product Detail Page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" 
					  rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" 
					  crossorigin="anonymous">
		<link rel = "stylesheet" type = "text/css" href = "../css/style.css">
	</head>
	<body>
		<div class = "container">
			<div>
				<h1>Product Content</h1>
				<table class="table table-hover">
				  <tr>
				    <th>Product Name:</th>
				    <td>${theproduct.getProdName()}</td>
				  </tr>
				  <tr>
				    <th>Category:</th>
				    <td>${theproduct.getCategory()}</td>
				  </tr>
				  <tr>
				    <th>Product Brand:</th>
				    <td>${theproduct.getBrand()}</td>
				  </tr>
				  <tr>
				    <th>Product Made Year:</th>
				    <td>${theproduct.getMadeDate()}</td>
				  </tr>
				  <tr>
				    <th>Product Condition:</th>
				    <td>${theproduct.getDamageSeverity()} Damage Rate</td>
				  </tr>
				  <tr>
				    <th>Product Market Price:</th>
				    <td>${theproduct.getMarketPrice()}</td>
				  </tr>
				  <tr>
				    <th>Product Current Price:</th>
				    <td>${theproduct.getCurrentPrice()}</td>
				  </tr>
				  <tr>
				    <th>Minimum Bidding Amount:</th>
				    <td>${theproduct.getBidPrice()}</td>
				  </tr>
				  <tr>
				    <th>Product Owner:</th>
				    <td>${theproduct.getOwner().getName()}</td>
				  </tr>
				  <tr>
				    <th>Product Location:</th>
				    <td>${theproduct.getState()}</td>
				  </tr>
				</table>
				<p>Product Detail:</p>
				<div class = "border border-secondary rounded prod-detail ">
					<p>${theproduct.getProdDetail()}</p>
				</div>
				
			</div>
			
			<div class="card-body">
			
				<c:choose>
					<c:when test = "${theproduct.getCurrentBidder().getId() == theguestid}">
						<div class = "askbid">
							<h3>Hi! ${theGuest.getName()}</h3>
							<p>You are the current bidder, do you want to raise your price?</p>
							<div class = "bidding_or_back">
								<form class = "button-form" action = "/bid/${theGuest.getId()}/${theproduct.getId()}" method = "post">
									<input type="submit"  class="btn btn-primary" value="Bid">
								</form>
								<form class = "button-form" action = "/loginguestpage" method = "post">
									<input type="submit"  class="btn btn-primary" value="Back to product page!">
								</form>
							</div>
						</div>				
					</c:when>
					
					<c:otherwise>
					 	<div class = "askbid">
							<h3>Hi! ${theGuest.getName()}</h3>
							<p>Current price is ${theproduct.getCurrentPrice()} Do you want to raise your price?</p>
							<div  class = "bidding_or_back">
								<form class = "button-form" action = "/bid/${theGuest.getId()}/${theproduct.getId()}" method = "post">
									<input type="submit"  class="btn btn-primary" value="Bidding!">
								</form>
								<form class = "button-form" action = "/loginguestpage" method = "post">
									<input type="submit"  class="btn btn-primary" value="Back to product page!">
								</form>
							</div>
						</div>					
					</c:otherwise>		
				</c:choose>		
			</div>
			
		</div>
	
	</body>
</html>