<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Your List</title>
</head>
<body>
	<h1>${nom}</h1>
	<ul>
		<c:forEach var="item" items="${items}">
			<li>${item.name}</li>
		</c:forEach>
	</ul>
	<form action="./AddItem" method="POST">
		<input name="itemName">
		<input type="hidden" name="listId" value="${id_list}">
		<button type="submit">add Item</button>
	</form>
</body>
</html>