<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Liste des loyers</title>
</head>
<body>
	<h1>Liste des loyers</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Montant</th>
				<th>Date de début</th>
				<th>Date de fin</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${loyers}" var="loyer">
				<tr>
					<td>${loyer.id}</td>
					<td>${loyer.montant}</td>
					<td>${loyer.dateDebut}</td>
					<td>${loyer.dateFin}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
