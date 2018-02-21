<%@page import="core.Article"%>
<%@page import="java.util.List"%>
<%@page import="core.Page"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un article dans une page</title>
</head>
<body>
	<div align="center">
		<h1>Ajout d'un article</h1>
		<p />
		<%
			Page unepage = (Page)request.getSession().getAttribute("PAGE");
		%>
		Thème de la page : <%=unepage.getTheme()%><br/>
		Sélectionnez un article dans cette liste : 
		<select name="article" size="1">
		<% 
			List<Article> articles = (List<Article>)request.getSession().getAttribute("ARTICLES"); 
			for (Article article : articles) {	
		%>
			<option value="<%=article.getCode()%>"><%=article.getTitre() %></option>
		<% } %>
		</select>
	</div>
</body>
</html>