<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<style type="text/css">
	<%@ include file="/WEB-INF/style.css" %>
</style>
</head>
<body>
<%@include file="menu.jsp" %>
	<br><br><br>
	<form method="get" action="Search">
		<input name="searchString" id="searchStringBox" value="${param.searchString}" />
		<input type="submit" id="filterButton" value="Filtreeri" />
		<br/><br/>
		<table class="listTable" id="listTable">
			<thead>
				<tr>
					<th scope="col">Nimi</th>
					<th scope="col">Kood</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope['data']}" var="each">
					<tr>
						<td>
						<div id="row_${each.code}">${each.name}</div></td>
						<td>${each.code}</td>
						<td><a href="Search?do=delete&id=${each.id}" id="delete_${each.code}">Kustuta</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

</body>
</html>