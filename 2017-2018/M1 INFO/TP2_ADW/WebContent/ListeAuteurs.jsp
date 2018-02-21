<%@page import="core.Auteur"%>
<%@page import="java.util.List"%>
<%@page import="dao.DAOAuteur"%>
<%@page import="dao.jpa.DAOAuteurJPA"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des auteurs</title>
</head>
<body>
	<div align="center">
		<h1>Liste des auteurs</h1>
		<p />
		<%
			DAOAuteur dao = DAOAuteurJPA.getInstance();
			List<Auteur> auteurs = dao.loadAll();
		%>
		<table border="1">
			<%
				for (Auteur auteur : auteurs) {
			%>
			<tr>
				<td><%=auteur.getCode()%></td>
				<td><%=auteur.getNom()%></td>
				<td><%=auteur.getStatut()%></td>
				<td><%=auteur.getDateEmbauche()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>