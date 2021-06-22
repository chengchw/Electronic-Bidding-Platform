<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Provider Login Register</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" 
					  rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" 
					  crossorigin="anonymous">
		<link rel = "stylesheet" type = "text/css" href = "../css/style.css">
	</head>
	<body>
		<div class = "container">
			<h1>Hello Provider</h1>
				<div class = "form-group">
				<p>${regeror}</p>
				
				<h3>Register</h3>
				<form:form action = "/registprovider" method = "post" modelAttribute ="newprovider">
					<p>
						<form:label path = "name">Name:</form:label>
						<form:errors path="name"/>
						<form:input path="name"/>
					</p>
					
					<p>
						Where do you live:
						<form:errors path="state"/>
						<form:select path="state">
							
							<c:forEach items = "${states}" var = "state">
								<option value="${state}">${state}</option>
							</c:forEach>
							
						</form:select>
					</p>	
					
					
					<%-- <p>
						<form:label path = "state">Where Do you Live:</form:label>
						<form:errors path="state"/>
						<form:input path="state"/>
					</p> --%>
					
					<p>
						<form:label path = "email">Email:</form:label>
						<form:errors path="email"/>
						<form:input path="email"/>
					</p>
									
					<p>
						<form:label path = "password">Password:</form:label>
						<form:errors path="password"/>
						<form:password path="password"/>
					</p>
					
					<p>
						<form:label path = "passwordConfirmation">PW Conf:</form:label>
						<form:errors path="passwordConfirmation"/>
						<form:password path="passwordConfirmation"/>
					</p>
					
					<input type="submit" value= "Register"/>
				</form:form>	
			</div>	
			
			<div>
			
				<p>${logerror}</p>
				<h3>Login</h3>
				
				<form method = "post" action = "/loginprovider">
				
					<p>
						<label for = "email">Email</label> 
						<input type ="text" id = "email" name = "inputemail"/>
					</p>
					
					<p>
						<label for = "password">Password</label> 
						<input type ="password" id = "password" name = "inputpassword"/>
					</p>
					<input type = "submit" value ="Login!"/>
		
				</form>
			
			</div>
	
		</div>
	
	</body>
</html>