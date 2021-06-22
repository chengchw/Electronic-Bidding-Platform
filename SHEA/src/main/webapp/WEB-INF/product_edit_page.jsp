<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Edit Product here</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" 
					  rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" 
					  crossorigin="anonymous">
		<link rel = "stylesheet" type = "text/css" href = "../css/style.css">
	</head>
	<body>
	
		<div class = "container">
		
			<div class = "form-group">
				<p>${regeror}</p>
				
				<h3>Editing Product</h3>
				<form:form action = "/editproduct" method = "post" modelAttribute ="editprod">
				
						
						<form:input type = "hidden" path="id" var = "${editprod.getId()}"/>
						<form:input type = "hidden" path="owner" var = "${editprod.getOwner()}"/>
						<form:input type = "hidden" path="currentPrice" var = "${editprod.getCurrentPrice()}"/>
						<form:input type = "hidden" path="bidders" var = "${editprod.getBidders()}"/>
						<form:input type = "hidden" path="historyPrice" var = "${editprod.getHistoryPrice()}"/>
						<%-- <form:input type = "hidden" path="guestPriceMapping" var = "${editprod.getGuestPriceMapping()}"/> --%>
						<form:input type = "hidden" path="currentBidId" var = "${editprod.getCurrentBidId()}"/>
					<p>
					
						<form:label path = "prodName">Product Name:</form:label>
						<form:errors path="prodName"/>
						<form:input path="prodName"/>
					</p>
					
					<p>
						
						<form:label path = "category">Product Category:</form:label>
						<form:errors path="category"/>
						<form:input path="category" /><br>
						(cell phone, vedio game, TV...etc)
					</p>
					
					
					
					<p>
						What is the brand of product ?<br>
						<form:label path = "brand">Product Brand:</form:label>
						<form:errors path="brand"/>
						<form:select path="brand">
							
							<c:forEach items = "${allbrand}" var = "brand">
								<option value="${brand}">${brand}</option>
							</c:forEach>
							
						</form:select>
					</p>
					
					<p>
						When was the product made ?<br>
						<form:errors path="madeDate"/>
						<form:select path="madeDate">
							
							<c:forEach items = "${madeyear}" var = "year">
								<option value="${year}">${year}</option>
							</c:forEach>
							
						</form:select>
					</p>
					
					<p>
						How's the condition of the product ?
						If the shape of product is bad, we don't recommend to place on-shelf....<br>
						<form:label path="damageSeverity">Damage Rate:</form:label>
						<form:errors path="damageSeverity"/>
						<form:select path="damageSeverity">
							
							<c:forEach items = "${damagerate}" var = "rate">
								<option value="${rate}">${rate}</option>
							</c:forEach>
							
						</form:select>
					</p>
				
					<p>
						Where is your product ?<br>
						<form:errors path="state"/>
						<form:select path="state">
							
							<c:forEach items = "${states}" var = "state">
								<option value="${state}">${state}</option>
							</c:forEach>
							
						</form:select>
					</p>
					
					<p>
						What is market price of the product?<br>
						<form:label path = "marketPrice">Market Price:</form:label>
						<form:errors path="marketPrice"/>
						<form:input path="marketPrice"/>
					</p>
					
					<p>
						How much is the initial price ?
						(Recommended initial price should less than 50% of market price )<br>
						<form:label path = "iniPrice">Initial Price:</form:label>
						<form:errors path="iniPrice"/>
						<form:input path="iniPrice"/>
					</p>
					
					<p>
						What will be the minimum amount of each bidding?
						(Recommended bidding amount should be 5% of initial price)<br>
						<form:label path = "bidPrice">Bidding Amount:</form:label>
						<form:errors path="bidPrice"/>
						<form:input path="bidPrice"/>
					</p>
					
					<p class = "error-message">
						${showerrors}<br>
						Put the expiration date of the bidding<br>
						<form:label path = "expireDate">Expiration Date</form:label>
						<form:errors path="expireDate"/>
						<form:input type = "date"   path="expireDate"/>
					</p>
					
					
					<p>
						Put your product detail here <br>
						<form:label path = "prodDetail">Product Detail:</form:label><br>
						<form:errors path="prodDetail"/>
						<form:textarea  path="prodDetail" rows="4" cols="80"/>
					</p>
						
					
					
					<%-- <p>
						<form:label path = "state">Where Do you Live:</form:label>
						<form:errors path="state"/>
						<form:input path="state"/>
					</p> --%>
					<input type="submit" class="btn btn-primary" value= "Edit Product"/>
				</form:form><br>
				<form action = "/delete/${editprod.getId()}"  method = post>
					<input class="btn btn-primary" type = "submit" value = "Delete">
				</form>	
			</div>
			
			<div class = guest_info>
				<h3>Current Bidder and Price</h3>
				<table class="table table-hover">
				    <thead>
				        <tr>
				        	<th>Index</th>
				            <th>Bidder</th>
				            <th>Bid Price</th>
				        </tr>
				    </thead>
				    <tbody>
				    <!-- product list -->
				        <c:forEach items="${bidderlist}" var="bidder" varStatus="loop">
				        <tr>
				        	<td><c:out value = "${loop.index+1}"></c:out></td>
				        	<td><c:out value = "${bidder.getName()}"></c:out></td>
				        	<td><c:out value = "${editprod.getGuestPriceMapping().get(bidder)}"></c:out></td>
				        	
				        </tr>
				        </c:forEach>
			    	</tbody>
		   		</table>
			
			</div>
		</div>
	</body>
</html>