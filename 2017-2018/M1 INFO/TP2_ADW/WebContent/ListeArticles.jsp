<%@page import="core.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des articles</title>
</head>
<body>
	<div align="center">
		<h1>Liste des articles</h1>
		<p />
		<%
			List<Article> articles = (List<Article>)request.getSession().getAttribute("ARTICLES");
			System.out.println("Les articles : "+articles);
		%>
		<table border="1">
			<%
				for (Article article : articles) {
			%>
			<tr>
				<td><%=article.getCode()%></td>
				<td><%=article.getTitre()%></td>
				<td><%=article.getTarif()%></td>
				<td><%=article.getAuteur().getNom()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

</body>
</html>