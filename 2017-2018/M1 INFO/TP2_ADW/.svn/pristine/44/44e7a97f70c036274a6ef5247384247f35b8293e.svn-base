<%@page import="core.Page"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Affichage d'une page</title>
</head>
<body>
	<div align="center">
		<h1>Résumé d'une page</h1>
		<p />
		<%
			Page unepage = (Page)request.getSession().getAttribute("PAGE");
			if (unepage==null) {
		%>
		Page introuvable
		<%	}
			else {
		%>
		<table border="1">
			<tr><td>Code</td><td><%=unepage.getCode()%></td></tr>
			<tr>	<td>Theme</td><td><%=unepage.getTheme()%></td></tr>
			<tr>	<td>Tarif</td><td><%=unepage.getTarif()%></td></tr>
		</table>
		<%
			}
		%>
	</div>
</body>
</html>