<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Add note</title>
		<link rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
			crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a class="btn btn-primary" href="#" role="button" onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
				 <form:form method="POST" modelAttribute="noteForm" class="form-signin">
		            
		            <h2 class="form-signin-heading">Add note</h2>
		           
		            <spring:bind path="name">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="name" class="form-control" placeholder="name"
		                                autofocus="true"></form:input>
		                    <form:errors path="name"></form:errors>
		                </div>
		            </spring:bind>
		
		            <spring:bind path="text">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="text" class="form-control" placeholder="text"></form:input>
		                    <form:errors path="text"></form:errors>
		                </div>
		            </spring:bind>
		
		            <button class="btn btn-lg btn-primary btn-block" type="submit">Add new note</button>
		        </form:form>
			</c:if>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
	</body>
</html>