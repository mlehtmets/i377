<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css"> @import url("/i377/WebContent/static/css/style.css"); </style>
<title>Add</title>
<style type="text/css">
	<%@ include file="/static/css/style.css" %>
</style>
</head>
<body>
<ul id="menu">
	<li><a href="/mlehtmets/Search" id="menu_Search">Otsi</a></li>
	<li><a href="/mlehtmets/Add" id="menu_Add">Lisa</a></li>
	<li><a href="/mlehtmets/Admin?do=clear_data" id="menu_ClearData">Tühjenda</a></li>
	<li><a href="/mlehtmets/Admin?do=insert_data" id="menu_InsertData">Sisesta näidisandmed</a></li>
</ul>
	<br><br><br>
	<form method="post" action="/mlehtmets/Add">
		<table class="formTable" id="formTable">
			<tbody>
				<tr>
					<td>Nimi:</td>
					<td><input name="name" id="nameBox" /></td>
				</tr>
				<tr>
					<td>Kood:</td>
					<td><input name="code" id="codeBox" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><br/>
						<input type="submit" value="Lisa" id="addButton" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>